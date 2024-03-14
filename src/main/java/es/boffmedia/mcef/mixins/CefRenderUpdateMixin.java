package es.boffmedia.mcef.mixins;

import com.mojang.blaze3d.matrix.MatrixStack;
import es.boffmedia.mcef.MCEF;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class CefRenderUpdateMixin {
    @Inject(at = @At("HEAD"), method = "updateCameraAndRender")
    public void preRender(float partialTicks, long nanoTime, boolean renderWorldIn, CallbackInfo ci) {
        if (MCEF.isInitialized()) {
            MCEF.getApp().getHandle().N_DoMessageLoopWork();
        }
    }
}