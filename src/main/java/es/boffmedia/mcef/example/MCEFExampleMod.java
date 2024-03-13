package es.boffmedia.mcef.example;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraft.client.util.InputMappings;
import org.lwjgl.glfw.GLFW;

public class MCEFExampleMod {
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static final KeyBinding KEY_BINDING = new KeyBinding(
            "Open Browser",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM.getOrMakeInput(GLFW.GLFW_KEY_F10),
            "key.categories.misc"
    );

    public MCEFExampleMod() {
        ClientRegistry.registerKeyBinding(KEY_BINDING);
        MinecraftForge.EVENT_BUS.addListener(this::onTick);
    }

    public void onTick(TickEvent.ClientTickEvent event) {
        if (KEY_BINDING.isPressed() && !(minecraft.currentScreen instanceof ExampleScreen)) {
            minecraft.displayGuiScreen(new ExampleScreen(
                    new StringTextComponent("Example Screen")
            ));
        }
    }
}