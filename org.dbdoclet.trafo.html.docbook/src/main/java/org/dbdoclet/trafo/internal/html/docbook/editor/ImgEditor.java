/*
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.service.FileServices;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.tag.docbook.Entry;
import org.dbdoclet.tag.docbook.ImageData;
import org.dbdoclet.tag.docbook.ImageObject;
import org.dbdoclet.tag.docbook.Link;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Screen;
import org.dbdoclet.tag.docbook.Term;
import org.dbdoclet.tag.docbook.ULink;
import org.dbdoclet.tag.html.Img;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.docbook.DbtConstants;
import org.dbdoclet.trafo.param.Param;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.ImageServices;

public class ImgEditor extends DocBookEditor {

	private static Log logger = LogFactory.getLog(ImgEditor.class);

	private static HashMap<String, String> validFormatMap;

	static {

		validFormatMap = new HashMap<String, String>();

		validFormatMap.put("BASE64", "BASE64");
		validFormatMap.put("BMP", "BMP");
		validFormatMap.put("CGM-BINARY", "CGM-BINARY");
		validFormatMap.put("CGM-CHAR", "CGM-CHAR");
		validFormatMap.put("CGM-CLEAR", "CGM-CLEAR");
		validFormatMap.put("DITROFF", "DITROFF");
		validFormatMap.put("DVI", "DVI");
		validFormatMap.put("EPS", "EPS");
		validFormatMap.put("EQN", "EQN");
		validFormatMap.put("FAX", "FAX");
		validFormatMap.put("GIF", "GIF");
		validFormatMap.put("GIF87a", "GIF87a");
		validFormatMap.put("GIF89a", "GIF89a");
		validFormatMap.put("IGES", "IGES");
		validFormatMap.put("JPEG", "JPEG");
		validFormatMap.put("JPG", "JPG");
		validFormatMap.put("linespecific", "linespecific");
		validFormatMap.put("PCX", "PCX");
		validFormatMap.put("PIC", "PIC");
		validFormatMap.put("PNG", "PNG");
		validFormatMap.put("PS", "PS");
		validFormatMap.put("SGML", "SGML");
		validFormatMap.put("SVG", "SVG");
		validFormatMap.put("TBL", "TBL");
		validFormatMap.put("TEX", "TEX");
		validFormatMap.put("TIFF", "TIFF");
		validFormatMap.put("WMF", "WMF");
		validFormatMap.put("WPG", "WPG");
	}

	public void createFoImageData(DocBookElement parent,
			DocBookTagFactory dbfactory, List<String> imageDataFormats,
			Img img, File file) throws IOException {

		String fileRef = FileServices.normalizePath(file.getPath());
		fileRef = FileServices.getFileBase(fileRef);

		if (FileServices.isAbsolutePath(fileRef)) {

			fileRef = FileServices.normalizePath(fileRef);

			if (fileRef.startsWith("/")) {
				fileRef = "file://" + fileRef;
			} else {
				fileRef = "file:///" + fileRef;
			}
		}

		int index = 0;

		for (String format : imageDataFormats) {

			ImageObject image = dbfactory.createImageObject();

			if (index == 0) {
				image.setRole("fo");
			} else {
				image.setRole("fo-" + format.toLowerCase());
			}

			String align = img.getAlign();

			ImageData data = dbfactory.createImageData();

			data.setScaleFit(true);
			data.setWidth("100%");
			data.setContentDepth("100%");
			data.setFormat(format);

			if (align != null && align.length() > 0) {
				data.setAlign(validateAlign(align));
			}

			data.setFileRef(fileRef + "." + format.toLowerCase());

			image.appendChild(data);
			parent.appendChild(image);
			index++;
		}
	}

	public void createHtmlImageData(DocBookElement parent,
			DocBookTagFactory dbfactory, List<String> imageDataFormats,
			Img img, File file) throws IOException {

		String fileRef = FileServices.normalizePath(file.getPath());
		logger.debug("Parameter fileRef=" + fileRef);

		if (FileServices.isAbsolutePath(fileRef)) {

			fileRef = FileServices.normalizePath(fileRef);

			if (fileRef.startsWith("/")) {
				fileRef = "file://" + fileRef;
			} else {
				fileRef = "file:///" + fileRef;
			}
		}

		fileRef = FileServices.getFileBase(fileRef);
		int index = 0;

		for (String format : imageDataFormats) {

			String width = img.getWidth();
			String height = img.getHeight();
			String align = img.getAlign();

			ImageObject image = dbfactory.createImageObject();

			if (index == 0) {
				image.setRole("html");
			} else {
				image.setRole("html-" + format.toLowerCase());
			}

			ImageData data = dbfactory.createImageData();
			data.setScaleFit(true);

			if (width != null && width.length() > 0) {
				data.setContentWidth(width);
			}

			if (height != null && height.length() > 0) {
				data.setContentDepth(height);
			}

			if (file.exists() && format.equalsIgnoreCase("BASE64")) {

				String fileName = FileServices.getFileBase(file) + ".base64";
				FileServices.writeFromString(new File(fileName),
						ImageServices.toXml(file));
			}

			data.setFormat(format);

			if (align != null && align.length() > 0) {
				data.setAlign(validateAlign(align));
			}

			String attr = fileRef + "." + format.toLowerCase();
			logger.debug("XML attribute fileRef=" + attr);
			data.setFileRef(attr);
			image.appendChild(data);
			parent.appendChild(image);
			index++;
		}
	}

	public ArrayList<String> createImageDataFormatList(
			ArrayList<String> paramList, String src) {

		ArrayList<String> formatList = new ArrayList<String>();

		for (String param : paramList) {

			if (param == null) {
				continue;
			}

			if (formatList.contains(param.toUpperCase()) == false) {
				formatList.add(param.toUpperCase());
			}
		}

		if (src != null) {

			String value = FileServices.getExtension(src);

			if (value != null && value.trim().length() > 0
					&& formatList.contains(value.toUpperCase()) == false) {
				formatList.add(0, value.toUpperCase());
			}
		}

		return formatList;
	}

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));

		DocBookTagFactory dbfactory = getTagFactory();
		Script script = getScript();

		Img img = (Img) getHtmlElement();

		Boolean useAbsoluteImagePath = script.isParameterOn(
				DbtConstants.SECTION_DOCBOOK,
				DbtConstants.PARAM_USE_ABSOLUTE_IMAGE_PATH, false);

		logger.debug("Parameter use-absolute-image-path is set to "
				+ useAbsoluteImagePath);

		ArrayList<String> imageDataFormats = createImageDataFormatList(
				script.getTextParameterList(DbtConstants.SECTION_DOCBOOK,
						DbtConstants.PARAM_IMAGEDATA_FORMATS,
						new ArrayList<String>()), img.getSrc());

		String imagePath = "./img";

		Param<?> param = script
				.getVariable(DbtConstants.PARAM_IMAGE_PATH);

		if (param != null) {
			imagePath = param.getValueAsText();
		} else {
			imagePath = script.getTextParameter(DbtConstants.SECTION_DOCBOOK,
					DbtConstants.PARAM_IMAGE_PATH, "");
		}

		logger.debug("Configuration property imagePath is set to " + imagePath);

		String src = img.getSrc();
		logger.debug("Attribute src has a value of " + src);

		if ((src == null) || (src.length() == 0)) {
			return finalizeValues();
		}

		if (imagePath != null && imagePath.length() > 0
				&& FileServices.isAbsolutePath(src) == false) {
			src = FileServices.appendFileName(imagePath, src);
		}

		validateSrc(src);

		DocBookElement media;
		DocBookElement parent = getParent();

		if (parent instanceof Emphasis || parent instanceof Entry
				|| parent instanceof Para || parent instanceof Screen
				|| parent instanceof Term || parent instanceof ULink
				|| parent instanceof Link) {

			media = dbfactory.createInlineMediaObject();
			parent.appendChild(media);
			media.setParentNode(getCurrent());

			setAnything(media.getParentNode());

		} else {

			DocBookElement figure;

			String title = img.getTitle();

			if ((title != null) && (title.length() > 0)) {
				figure = dbfactory.createFigure(title);
			} else {
				figure = dbfactory.createInformalFigure();
			}

			figure.setParentNode(parent);

			if (figure.validate()) {

				parent.appendChild(figure);

				media = dbfactory.createMediaObject();
				figure.appendChild(media);

				setAnything(figure);

			} else {

				traverse(true);
				return finalizeValues();
			}
		}

		traverse(false);
		setCurrent(parent);

		File file = new File(src);

		if (useAbsoluteImagePath) {
			file = file.getAbsoluteFile();
		}

		try {

			createHtmlImageData(media, dbfactory, imageDataFormats, img, file);
			createFoImageData(media, dbfactory, imageDataFormats, img, file);

		} catch (IOException oops) {
			throw new EditorException(oops);
		}

		return finalizeValues();
	}

	public String validateFormat(String format) {

		if (format == null) {

			throw new IllegalArgumentException("Parameter format is null!");
		}

		format = format.toUpperCase();

		if (validFormatMap.get(format) != null) {
			return format;
		} else {
			return "JPG";
		}
	}
}
