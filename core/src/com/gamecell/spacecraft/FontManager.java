package com.gamecell.spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Clase para cargar la fuente y posteriormente usarla para mostrar texto
 * @author Josue Javier Campos Fernandez
 */
public class FontManager  {
    public static BitmapFont font;

    /**
     * Carga el archivo fnt en un BitmapFont, aplicandole una escala a la fuente
     */
    public static void load() {
        FileHandle fontFile = Gdx.files.internal("Fonts/space.fnt");
        font = new BitmapFont(fontFile, false);
        font.getData().setScale(0.4f);
    }

}
