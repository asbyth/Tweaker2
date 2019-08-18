package net.asbyth.tweaker.asm;

import net.asbyth.tweaker.loader.transformer.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ScreenShotHelperTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"net.minecraft.util.ScreenShotHelper"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods) {
            String methodName = mapMethodName(classNode, methodNode);

            if (methodName.equals("saveScreenshot") &&
                    methodNode.desc.startsWith("(Ljava/io/File;Ljava/lang/String;")) {
                methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), getAsyncScreenshotSaver());
            }
        }
    }

    private InsnList getAsyncScreenshotSaver() {
        InsnList list = new InsnList();
        list.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/asbyth/tweaker/config/Settings", "ASYNCSCREENSHOTS", "Z"));
        LabelNode labelNode = new LabelNode();
        list.add(new JumpInsnNode(Opcodes.IFEQ, labelNode));
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        list.add(new VarInsnNode(Opcodes.ALOAD, 1));
        list.add(new VarInsnNode(Opcodes.ALOAD, 2));
        list.add(new VarInsnNode(Opcodes.ALOAD, 3));
        list.add(new VarInsnNode(Opcodes.ALOAD, 4));
        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "net/asbyth/tweaker/implementation/ScreenshotHelperHook", "handleScreenshot",
                "(Ljava/io/File;Ljava/lang/String;IILnet/minecraft/client/shader/Framebuffer;)Lnet/minecraft/util/IChatComponent;", false));
        list.add(new InsnNode(Opcodes.ARETURN));
        list.add(labelNode);
        return list;
    }
}
