package components.animation;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
/*XXX ez az osztály átírásra szorul, mivel nem objektum orientáltan mûködik. */

/**
* Az animációkhoz tartozó xml parsolásáért felelõs osztály.
* 
* @author Ács Ádám
*/
public class StaXParser
{
	static final String IMAGE = "image";
	static final String EXTENSION = "extension";
	static final String MODE = "mode";
	static final String FILE = "file";
	static final String WIDTH = "width";
	static final String HEIGHT = "height";
	static final String POSX = "posx";
	static final String POSY = "posy";
	static final String COUNT = "count";
	static final String PERROW = "perrow";
	static final String ROWS = "rows";
	static final String COLS = "cols";
	private BufferedImage image = null;

	/**
	 * beolvassa az animáció xml-t és elõállítja belõle az animációt.
	 * @param animationFile
	 * @throws IOException
	 */
	public void readConfig(String animationFile) throws IOException {
		boolean sheet;
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(animationFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			sheet = false;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals(IMAGE)) {
						Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(EXTENSION)) {
								AnimationFactory.extension = attribute.getValue();
							}
						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart().equals(MODE)) {
							event = eventReader.nextEvent();
							AnimationFactory.mode = event.asCharacters().getData();
							if (event.asCharacters().getData().equals("spritesheet")) {
								sheet = true;
							}
							continue;
						}
					}
					if (event.asStartElement().getName().getLocalPart().equals(FILE)) {
						event = eventReader.nextEvent();
						AnimationFactory.fileName = event.asCharacters().getData();
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(WIDTH)) {
						event = eventReader.nextEvent();
						AnimationFactory.width = Integer.parseInt(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(HEIGHT)) {
						event = eventReader.nextEvent();
						AnimationFactory.height = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart().equals(POSX)) {
						event = eventReader.nextEvent();
						AnimationFactory.posX = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart().equals(POSY)) {
						event = eventReader.nextEvent();
						AnimationFactory.posY = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart().equals(COUNT)) {
						event = eventReader.nextEvent();
						AnimationFactory.count = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart().equals(PERROW)) {
						event = eventReader.nextEvent();
						AnimationFactory.perrow = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart().equals(ROWS)) {
						event = eventReader.nextEvent();
						AnimationFactory.rows = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart().equals(COLS)) {
						event = eventReader.nextEvent();
						AnimationFactory.cols = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
				}
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart().equals(IMAGE)) {
						if (sheet) {
							createSheet(AnimationFactory.fileName);
						}
						else {
							image = ImageIO.read(new File(AnimationFactory.fileName));
							AnimationFactory.images.add(image);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	/**
	 * beállítja a factory állapotát, hogy az képes legyen animációt létrehozni
	 * @param file
	 * @throws IOException
	 */
	public static void createSheet(String file) throws IOException {
		int num = 0;
		int c = AnimationFactory.count;
		int p = AnimationFactory.perrow;
		int w = AnimationFactory.width;
		int h = AnimationFactory.height;
		int rows = AnimationFactory.rows;
		int cols = AnimationFactory.cols;
		int row = 0;
		
		if (c % p == 0) {
			row = c / p;
		}
		else {
			row = (c / p) + 1;
		}
		
		BufferedImage bigImg = ImageIO.read(new File(file));
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < p && num != c; j++) {
				BufferedImage currentImg = bigImg.getSubimage(j * w + cols, i * h + rows, w, h);
				AnimationFactory.images.add(currentImg);
				num++;
			}
		}
	}
}
