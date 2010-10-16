package cz.fi.muni.xkremser.editor.server.modelHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.w3c.dom.Document;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import cz.fi.muni.xkremser.editor.fedora.FedoraAccess;
import cz.fi.muni.xkremser.editor.fedora.utils.DCUtils;
import cz.fi.muni.xkremser.editor.shared.valueobj.AbstractDigitalObjectDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.DublinCore;
import cz.fi.muni.xkremser.editor.shared.valueobj.InternalPartDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.PageDetail;

public class InternalPartHandler extends DigitalObjectHandler {
	private transient final PageHandler pageHandler;

	@Inject
	public InternalPartHandler(Log logger, @Named("securedFedoraAccess") FedoraAccess fedoraAccess, PageHandler pageHandler) {
		super(logger, fedoraAccess);
		this.pageHandler = pageHandler;
	}

	@Override
	public AbstractDigitalObjectDetail getDigitalObject(String uuid) {
		// uuid = "0eaa6730-9068-11dd-97de-000d606f5dc6";
		InternalPartDetail detail = new InternalPartDetail();
		DublinCore dc = new DublinCore();
		ArrayList<PageDetail> pages = new ArrayList<PageDetail>();
		Document dcDocument = null;
		try {
			dcDocument = getFedoraAccess().getDC(uuid);
			List<String> pageUuids = getFedoraAccess().getIsOnPagesUuid(uuid);
			// List<String> pageUuids = getFedoraAccess().getPagesUuid(uuid);
			for (String pageUuid : pageUuids) {
				pages.add((PageDetail) pageHandler.getDigitalObject(pageUuid));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dc.setTitle(DCUtils.titleFromDC(dcDocument));
		dc.setCreator(Arrays.asList(DCUtils.creatorsFromDC(dcDocument)));
		detail.setDc(dc);
		detail.setPages(pages);

		return detail;
	}

}