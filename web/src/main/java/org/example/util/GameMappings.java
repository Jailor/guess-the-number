package org.example.util;

public final class GameMappings {
    private GameMappings() {throw  new UnsupportedOperationException("don't instantiate me");}

    public static final String PLAY = "play";
    public static final String REDIRECT_PLAY = "redirect:/" + PLAY;
    public static final String RESTART = "restart";
    public static final String HOME = "/";
}
