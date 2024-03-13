package es.boffmedia.mcef.mixins;

import es.boffmedia.mcef.MCEF;
import es.boffmedia.mcef.internal.MCEFDownloadListener;
import es.boffmedia.mcef.internal.MCEFDownloaderMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class CefInitMixin {
    @Shadow
    public abstract void displayGuiScreen(@Nullable Screen guiScreen);

    @Inject(at = @At("HEAD"), method = "displayGuiScreen", cancellable = true)
    public void redirScreen(Screen guiScreen, CallbackInfo ci) {
        System.out.println("MCEF.isInitialized(): " + MCEF.isInitialized());
        if (!MCEF.isInitialized()) {
            if (guiScreen instanceof MainMenuScreen) {
                // If the download is done and didn't fail
                if (MCEFDownloadListener.INSTANCE.isDone() && !MCEFDownloadListener.INSTANCE.isFailed()) {
                    Minecraft.getInstance().execute((() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MCEF.initialize();
                    }));
                }
                // If the download is not done and didn't fail
                else if (!MCEFDownloadListener.INSTANCE.isDone() && !MCEFDownloadListener.INSTANCE.isFailed()) {
                    displayGuiScreen(new MCEFDownloaderMenu((MainMenuScreen) guiScreen));
                    ci.cancel();
                }
                // If the download failed
                else if (MCEFDownloadListener.INSTANCE.isFailed()) {
                    MCEF.getLogger().error("MCEF failed to initialize!");
                }
            }
        }
    }
}