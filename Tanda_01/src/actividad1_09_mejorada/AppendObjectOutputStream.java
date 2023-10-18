package actividad1_09_mejorada;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendObjectOutputStream extends ObjectOutputStream {
    public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    // Override writeStreamHeader to prevent writing the header
    @Override
    protected void writeStreamHeader() throws IOException {
        // Resetting the header intentionally left blank
        reset();
    }
}
