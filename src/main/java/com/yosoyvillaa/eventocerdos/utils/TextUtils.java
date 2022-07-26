package com.yosoyvillaa.eventocerdos.utils;

import org.bukkit.ChatColor;

public class TextUtils {

    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
