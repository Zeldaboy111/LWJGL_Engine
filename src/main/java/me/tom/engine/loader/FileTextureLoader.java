package me.tom.engine.loader;

import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

final class FileTextureLoader implements TextureLoader {
    private final String fileName;
    FileTextureLoader(final String fileName) throws ObjectLoaderException {
        if(fileName == null || fileName.trim().equals("")) {
            throw new ObjectLoaderException("Cannot create FileTextureLoader: file null");
        }

        this.fileName = fileName;
    }

    @Override
    public int loadAndProcess() throws ObjectLoaderException {
        final LoadingTexture texture = loadTexture(fileName);
        return processTexture(texture);
    }

    /**
     *  Method used to load a new {@link LoadingTexture} from the given filename
     * @param fileName - Name from the file to load a {@link LoadingTexture} from
     * @return Loaded {@link LoadingTexture}
     * @throws ObjectLoaderException Thrown if any error occurs
     */
    private static LoadingTexture loadTexture(final String fileName) throws ObjectLoaderException {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            final IntBuffer width = stack.mallocInt(1);
            final IntBuffer height = stack.mallocInt(1);
            final IntBuffer channels = stack.mallocInt(1); // Number of channels

            // Load the texture, desired channels four (RGBA)
            ByteBuffer buffer = STBImage.stbi_load(fileName, width, height, channels, 4);
            if(buffer == null) {
                throw new ObjectLoaderException(String.format("Cannot load Image %s: %s",
                        fileName,
                        STBImage.stbi_failure_reason()));
            }

            return new LoadingTexture(width, height, buffer);
        }
    }

    /**
     *  Used to process a {@link LoadingTexture} thereby making it usable and giving the proper looks
     * @param texture - {@link LoadingTexture} to be processed
     * @return Id from the processed {@link LoadingTexture}
     */
    private static int processTexture(final LoadingTexture texture) {
        final int id = GL30.glGenTextures();

        // Bind texture
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, id);

        // Process the LoadingTexture
        GL30.glPixelStorei(GL30.GL_UNPACK_ALIGNMENT, 1);
        GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, texture.width, texture.height,
                    0, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, texture.buffer);

        GL30.glGenerateMipmap(GL30.GL_TEXTURE_2D);
        STBImage.stbi_image_free(texture.buffer);

        // Return the id
        return id;
    }

    /**
     *  Class used to store the information from a Texture currently being loaded/processed
     */
    private static class LoadingTexture {
        final int width, height;
        final ByteBuffer buffer;
        LoadingTexture(final IntBuffer width, final IntBuffer height, final ByteBuffer buffer) {
            this.width = width.get();
            this.height = height.get();
            this.buffer = buffer;
        }
    }
}
