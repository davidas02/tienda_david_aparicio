package service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;

public class ServicioArticulo {
	public static byte[] convertirImagen(Blob image) {
		try {
			InputStream inputStream = image.getBinaryStream(); // inputStream es de tipo InputStream
			BufferedImage imagenBuffered = ImageIO.read(inputStream); // imagenBuffered es de tipo BufferedImage
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(imagenBuffered, "jpg", byteArrayOutputStream);
			byte[] imagenBytes = byteArrayOutputStream.toByteArray();
			return imagenBytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
