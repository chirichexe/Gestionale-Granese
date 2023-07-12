package inventario.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import inventario.persistence.*;

public class MyMain {

	public static void main(String[] args) throws IOException, BadFileFormatException {
		
		
		
		String toRead =
				"scaffali: 45\r\n"
				+ "\r\n"
				+ "scaffale 1: 8007/CL,STN\r\n"
				+ "scaffale 1: 8007/CS, STN\r\n"
				+ "scaffale 1: 8007/NS,STN\r\n"
				+ "scaffale 1: 8007/B,STN ; 8008/NS,STN; 8008/NB, STN\r\n"
				+ "scaffale 1: 8008/M,STN\r\n"
				+ "scaffale 2: PATATA, BLT\r\n"
				+ "scaffale 2: PATATONE, BFF; POTOTI, GN";
		
		StringReader reader = new StringReader(toRead);
		MagazzinoReader magazzinoReader = new MagazzinoGraneseReader();
		Magazzino mag = magazzinoReader.leggiMagazzino(reader);
		System.out.println(mag.toString());
	}

}
