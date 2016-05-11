/* 
 * $Id$
 *
 * ### Copyright (C) 2005 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 *
 * RCS Information
 * Author..........: $Author$
 * Date............: $Date$
 * Revision........: $Revision$
 * State...........: $State$
 */
package org.dbdoclet.trafo.html.dita;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.xiphias.dom.ElementImpl;

public class LinkManager {

	private static Log logger = LogFactory.getLog(LinkManager.class);

	private final ArrayList<String> anchorIdList;
	private final ArrayList<ElementImpl> linkList;
	private final ArrayList<String> usedIdList;

	public LinkManager() {

		linkList = new ArrayList<ElementImpl>();
		anchorIdList = new ArrayList<String>();
		usedIdList = new ArrayList<String>();
	}

	public void addLink(ElementImpl link) {
		linkList.add(link);
	}

	public String createUniqueId(String str) {

		String id = str;

		id = ElementImpl.hardenId(id);

		String key = id;
		int counter = 1;

		while (anchorIdList.contains(key)) {

			key = id + "." + counter;
			counter++;
		}

		anchorIdList.add(key);
		logger.debug("Added reference " + key + ".");

		return key;
	}

	public ArrayList<ElementImpl> getLinkList() {
		return linkList;
	}

	public String getUniqueId(String str) {

		String id = str;

		logger.debug("#1 id=" + id);

		id = StringServices.cutPrefix(id, "#");

		id = ElementImpl.hardenId(id);
		logger.debug("#2 id=" + id);

		if (id == null || id.length() == 0) {
			id = "dbdoclet.id";
		}

		if (usedIdList.contains(id) == false) {
			usedIdList.add(id);
		}

		logger.debug("#3 id=" + id);
		return id;
	}

	public ArrayList<String> getUnresolvedIds() {

		ArrayList<String> list = new ArrayList<String>(usedIdList);
		list.removeAll(anchorIdList);

		return list;
	}

	public ArrayList<String> getUsedIdList() {

		return anchorIdList;
	}
}
