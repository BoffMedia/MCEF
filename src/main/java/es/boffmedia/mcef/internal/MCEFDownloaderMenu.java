package es.boffmedia.mcef.internal;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class MCEFDownloaderMenu extends Screen {
    private final MainMenuScreen menu;

    public MCEFDownloaderMenu(MainMenuScreen menu) {
        super(new StringTextComponent("MCEF is downloading required libraries..."));
        this.menu = menu;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(matrixStack);
        double cx = width / 2d;
        double cy = height / 2d;

        double progressBarHeight = 14;
        double progressBarWidth = width / 3d; // TODO: base off screen with (1/3 of screen)

        matrixStack.push();
        matrixStack.translate(cx, cy, 0);
        matrixStack.translate(-progressBarWidth / 2d, -progressBarHeight / 2d, 0);
        AbstractGui.fill(matrixStack, 0, 0, (int) progressBarWidth, (int) progressBarHeight, -1);
        AbstractGui.fill(matrixStack, 2, 2, (int) progressBarWidth - 2, (int) progressBarHeight - 2, -16777215);
        AbstractGui.fill(matrixStack, 4, 4, (int) ((progressBarWidth - 4) * MCEFDownloadListener.INSTANCE.getProgress()), (int) progressBarHeight - 4, -1);
        matrixStack.pop();

        String[] text = new String[]{
                MCEFDownloadListener.INSTANCE.getTask(),
                Math.round(MCEFDownloadListener.INSTANCE.getProgress() * 100) + "%",
        };

        int oSet = ((font.FONT_HEIGHT / 2) + ((font.FONT_HEIGHT + 2) * (text.length + 2))) + 4;
        matrixStack.push();
        matrixStack.translate(
                (int) (cx),
                (int) (cy - oSet),
                0
        );

        font.drawString(
                matrixStack,
                TextFormatting.GOLD + title.getString(),
                (int) -(font.getStringWidth(title.getString()) / 2d), 0,
                0xFFFFFF
        );
        int index = 0;
        for (String s : text) {
            if (index == 1) {
                matrixStack.translate(0, font.FONT_HEIGHT + 2, 0);
            }

            matrixStack.translate(0, font.FONT_HEIGHT + 2, 0);
            font.drawString(
                    matrixStack,
                    s,
                    (int) -(font.getStringWidth(s) / 2d), 0,
                    0xFFFFFF
            );
            index++;
        }
        matrixStack.pop();
    }

    @Override
    public void tick() {
        if (MCEFDownloadListener.INSTANCE.isDone() || MCEFDownloadListener.INSTANCE.isFailed()) {
            onClose();
            Minecraft.getInstance().displayGuiScreen(menu);
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }
}