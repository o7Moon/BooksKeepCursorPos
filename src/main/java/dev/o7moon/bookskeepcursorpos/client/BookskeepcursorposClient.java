package dev.o7moon.bookskeepcursorpos.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class BookskeepcursorposClient implements ClientModInitializer {

    static double x;
    static double y;
    static boolean hasSavedCursor = false;

    @Override
    public void onInitializeClient() {
        ScreenEvents.BEFORE_INIT.register((client, screen, width, height) -> {
            if (screen instanceof BookScreen) {
                if (hasSavedCursor){
                    client.mouse.x = x;
                    client.mouse.y = y;
                    InputUtil.setCursorParameters(client.getWindow().getHandle(), 212993, client.mouse.x, client.mouse.y);
                }
                ScreenEvents.remove(screen).register((_screen)->{
                    Mouse mouse = MinecraftClient.getInstance().mouse;
                    x = mouse.x;
                    y = mouse.y;
                    hasSavedCursor = true;
                });
            }
        });
    }
}
