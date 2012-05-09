/*
 * Metadata Editor
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Matous Jobanek (matous.jobanek@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */

package cz.mzk.editor.client.mods;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.form.fields.FormItem;

import cz.mzk.editor.client.mods.RoleTypeClient.RoleTermClient;

/**
 * @author Matous Jobanek
 * @version May 7, 2012
 */
public class ModsTypeClientManagerImpl
        implements ModsTypeClientManager {

    /** The original modsTypeClient */
    private ModsTypeClient modsTypeClient = null;

    /**
     * @param modsTypeClient
     */
    public ModsTypeClientManagerImpl(ModsTypeClient modsTypeClient) {
        super();
        this.modsTypeClient = modsTypeClient;
    }

    /**
     * @return
     */
    @Override
    public String getSubtitle() {
        if (getFirstTitleInfo() != null && isNotNullOrEmpty(getFirstTitleInfo().getSubTitle()))
            return getFirstTitleInfo().getSubTitle().get(0);
        return "";
    }

    /**
     * @return
     */
    @Override
    public String getTitle() {
        if (getFirstTitleInfo() != null && isNotNullOrEmpty(getFirstTitleInfo().getTitle()))
            return getFirstTitleInfo().getTitle().get(0);
        return "";
    }

    /**
     * @return
     */
    @Override
    public String getLevelName() {
        return (modsTypeClient.getId() != null) ? modsTypeClient.getId() : "";
    }

    /**
     * @return
     */
    @Override
    public String getType() {
        if (isNotNullOrEmpty(modsTypeClient.getGenre()) && modsTypeClient.getGenre().get(0).getType() != null)
            return modsTypeClient.getGenre().get(0).getType();
        return "";
    }

    /**
     * @return
     */
    @Override
    public String getPartName() {
        if (getFirstTitleInfo() != null && isNotNullOrEmpty(getFirstTitleInfo().getPartName()))
            return getFirstTitleInfo().getPartName().get(0);
        return "";
    }

    /**
     * @return
     */
    @Override
    public String getPartNumber() {
        if (getFirstTitleInfo() != null && isNotNullOrEmpty(getFirstTitleInfo().getPartNumber()))
            return getFirstTitleInfo().getPartNumber().get(0);
        return null;
    }

    /**
     * @return
     */
    private TitleInfoTypeClient getFirstTitleInfo() {
        if (isNotNullOrEmpty(modsTypeClient.getTitleInfo())) return modsTypeClient.getTitleInfo().get(0);
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getNote() {
        if (getFirstPhysDesc() != null && isNotNullOrEmpty(getFirstPhysDesc().getNote())
                && modsTypeClient.getPhysicalDescription().get(0).getNote().get(0).getValue() != null)
            return modsTypeClient.getPhysicalDescription().get(0).getNote().get(0).getValue();
        return "";
    }

    /**
     * @return
     */
    private PhysicalDescriptionTypeClient getFirstPhysDesc() {
        if (isNotNullOrEmpty(modsTypeClient.getPhysicalDescription()))
            return modsTypeClient.getPhysicalDescription().get(0);
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getDateIssued() {
        if (getFirstOriginInfo() != null && isNotNullOrEmpty(getFirstOriginInfo().getDateIssued())
                && getFirstOriginInfo().getDateIssued().get(0).getValue() != null)
            return getFirstOriginInfo().getDateIssued().get(0).getValue();
        return "";
    }

    /**
     * @return
     */
    private OriginInfoTypeClient getFirstOriginInfo() {
        if (isNotNullOrEmpty(modsTypeClient.getOriginInfo())) return modsTypeClient.getOriginInfo().get(0);
        return null;
    }

    private boolean isNotNullOrEmpty(List<?> objectList) {
        return objectList != null && !objectList.isEmpty() && objectList.get(0) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPublisher() {
        if (getFirstOriginInfo() != null && isNotNullOrEmpty(getFirstOriginInfo().getPublisher()))
            return getFirstOriginInfo().getPublisher().get(0);
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShelfLocator() {
        if (getFirstLocation() != null && isNotNullOrEmpty(getFirstLocation().getShelfLocator()))
            return getFirstLocation().getShelfLocator().get(0);
        return "";
    }

    /**
     * @return
     */
    private LocationTypeClient getFirstLocation() {
        if (isNotNullOrEmpty(modsTypeClient.getLocation())) return modsTypeClient.getLocation().get(0);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlace() {
        if (getFirstLocation() != null && isNotNullOrEmpty(getFirstLocation().getPhysicalLocation())
                && getFirstLocation().getPhysicalLocation().get(0).getValue() != null)
            return getFirstLocation().getPhysicalLocation().get(0).getValue();
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtent() {
        if (getFirstPhysDesc() != null && isNotNullOrEmpty(getFirstPhysDesc().getExtent()))
            return getFirstPhysDesc().getExtent().get(0);
        return "";
    }

    @Override
    public void modifyOriginInfoList(String publisher, String dateIssued) {
        /** Original type client part */
        List<OriginInfoTypeClient> newOriginalTypeClient;
        if (!isNotNullOrEmpty(modsTypeClient.getOriginInfo())) {
            newOriginalTypeClient = new ArrayList<OriginInfoTypeClient>();
            newOriginalTypeClient.add(new OriginInfoTypeClient());

        } else {
            newOriginalTypeClient = modsTypeClient.getOriginInfo();
        }

        /** Publisher list part */
        List<String> newPublisherList;
        if (!isNotNullOrEmpty(newOriginalTypeClient.get(0).getPublisher())) {
            newPublisherList = new ArrayList<String>();
        } else {
            newPublisherList = newOriginalTypeClient.get(0).getPublisher();
        }

        if (publisher == null || publisher.trim().equals("")) {
            if (isNotNullOrEmpty(newPublisherList)) newPublisherList.remove(0);
        } else {
            if (isNotNullOrEmpty(newPublisherList)) {
                newPublisherList.remove(0);
            }
            newPublisherList.add(0, publisher.trim());
        }
        newOriginalTypeClient.get(0).setPublisher(newPublisherList);

        /** Date issue list part */
        List<DateTypeClient> dateIssueList;
        if (!isNotNullOrEmpty(newOriginalTypeClient.get(0).getDateIssued())) {
            dateIssueList = new ArrayList<DateTypeClient>();
        } else {
            dateIssueList = modsTypeClient.getOriginInfo().get(0).getDateIssued();
        }

        if (dateIssued == null || dateIssued.toString().trim().equals("")) {
            if (isNotNullOrEmpty(dateIssueList)) dateIssueList.remove(0);
        } else {
            if (isNotNullOrEmpty(dateIssueList)) {
                dateIssueList.get(0).setValue(dateIssued.toString().trim());
            } else {

                DateTypeClient newDateType = new DateTypeClient();
                newDateType.setValue(dateIssued.toString().trim());
                dateIssueList.add(0, newDateType);
            }
        }
        newOriginalTypeClient.get(0).setDateIssued(dateIssueList);

        modsTypeClient.setOriginInfo(newOriginalTypeClient);
    }

    @Override
    public void modifyTitleInfoList(String title, String subtitle) {
        /** Title info part */
        List<TitleInfoTypeClient> newTitleInfo;
        if (!isNotNullOrEmpty(modsTypeClient.getTitleInfo())) {
            newTitleInfo = new ArrayList<TitleInfoTypeClient>();
            newTitleInfo.add(new TitleInfoTypeClient());
        } else {
            newTitleInfo = modsTypeClient.getTitleInfo();
        }

        /** Title part */
        if (title == null || title.trim().equals("")) {

            if (isNotNullOrEmpty(newTitleInfo.get(0).getTitle())) {
                newTitleInfo.get(0).getTitle().remove(0);
            }
        } else {
            if (isNotNullOrEmpty(newTitleInfo.get(0).getTitle())) {
                newTitleInfo.get(0).getTitle().remove(0);
            } else {
                newTitleInfo.get(0).setTitle(new ArrayList<String>());
            }
            newTitleInfo.get(0).getTitle().add(0, title.trim());
        }

        /** Subtitle part */
        if (subtitle == null || subtitle.trim().equals("")) {

            if (isNotNullOrEmpty(newTitleInfo.get(0).getSubTitle())) {
                newTitleInfo.get(0).getSubTitle().remove(0);
            }
        } else {
            if (isNotNullOrEmpty(newTitleInfo.get(0).getSubTitle())) {
                newTitleInfo.get(0).getSubTitle().remove(0);
            } else {
                newTitleInfo.get(0).setSubTitle(new ArrayList<String>());
            }
            newTitleInfo.get(0).getSubTitle().add(0, subtitle.trim());

        }
        modsTypeClient.setTitleInfo(newTitleInfo);
    }

    @Override
    public void modifyNameList(List<FormItem> authorPartsOfName1,
                               List<FormItem> authorPartsOfName2,
                               String author1,
                               String author2) {
        /** Name part */

        List<NameTypeClient> newNameList;
        if (!isNotNullOrEmpty(modsTypeClient.getName())) {
            newNameList = new ArrayList<NameTypeClient>();
        } else {
            newNameList = modsTypeClient.getName();
        }

        int index = 0;
        for (int i = 0; i < 2; i++) {

            List<NamePartTypeClient> nameParts = new ArrayList<NamePartTypeClient>();
            boolean allIsEmpty = true;
            for (FormItem item : (i == 0 ? authorPartsOfName1 : authorPartsOfName2)) {

                if (item.getValue() != null && !item.getValue().toString().trim().equals("")) {
                    NamePartTypeClient part = new NamePartTypeClient();
                    part.setValue(item.getValue().toString());
                    part.setType(item.getTitle().toString());
                    nameParts.add(part);
                    allIsEmpty = false;
                }
            }

            if (newNameList.size() <= index && (!allIsEmpty)) {
                newNameList.add(index, new NameTypeClient());
                newNameList.get(index).setRole(new ArrayList<RoleTypeClient>());
                newNameList.get(index).getRole().add(new RoleTypeClient());
                newNameList.get(index).getRole().get(0)
                        .setRoleTerm(new ArrayList<RoleTypeClient.RoleTermClient>());

                RoleTermClient newRoleTermCode = new RoleTermClient();
                RoleTermClient newRoleTermText = new RoleTermClient();

                if ((i == 0 ? author1 : author2) == null) {
                    newRoleTermCode.setValue("cre");
                    newRoleTermText.setValue("Author");

                } else {
                    newRoleTermCode.setValue("");
                    newRoleTermText.setValue((i == 0 ? author1 : author2));
                }

                newRoleTermCode.setType(CodeOrTextClient.CODE);
                newRoleTermText.setType(CodeOrTextClient.TEXT);
                newNameList.get(index).getRole().get(0).getRoleTerm().add(newRoleTermCode);
                newNameList.get(index).getRole().get(0).getRoleTerm().add(newRoleTermText);
            }

            if (allIsEmpty) {
                if (newNameList.size() > index) newNameList.remove(index);

            } else {

                newNameList.get(index).setNamePart(nameParts);
                if (newNameList.get(index).getType() == null) {
                    newNameList.get(index).setType(NameTypeAttributeClient.PERSONAL);
                }
                index++;
            }
        }
        modsTypeClient.setName(newNameList);
    }

    @Override
    public void modifyLocationList(String shelfLocator, String place) {
        /** Location list part */
        List<LocationTypeClient> newLocationList;
        if (!isNotNullOrEmpty(modsTypeClient.getLocation())) {
            newLocationList = new ArrayList<LocationTypeClient>();
            newLocationList.add(new LocationTypeClient());
        } else {
            newLocationList = modsTypeClient.getLocation();
        }

        /** Shelf locator part */
        if (shelfLocator == null || shelfLocator.trim().equals("")) {
            if (isNotNullOrEmpty(newLocationList.get(0).getShelfLocator())) {
                newLocationList.get(0).getShelfLocator().remove(0);
            }
        } else {
            if (isNotNullOrEmpty(newLocationList.get(0).getShelfLocator())) {
                newLocationList.get(0).getShelfLocator().remove(0);
            } else {
                newLocationList.get(0).setShelfLocator(new ArrayList<String>());
            }
            newLocationList.get(0).getShelfLocator().add(0, shelfLocator.trim());
        }

        /** Physical location part */
        if (place == null || place.trim().equals("")) {
            if (isNotNullOrEmpty(newLocationList.get(0).getPhysicalLocation())) {
                newLocationList.get(0).getPhysicalLocation().remove(0);
            }
        } else {
            if (isNotNullOrEmpty(newLocationList.get(0).getPhysicalLocation())) {
                newLocationList.get(0).getPhysicalLocation().get(0).setValue(place.trim());
            } else {
                newLocationList.get(0).setPhysicalLocation(new ArrayList<PhysicalLocationTypeClient>());
                newLocationList.get(0).getPhysicalLocation().add(new PhysicalLocationTypeClient());
                newLocationList.get(0).getPhysicalLocation().get(0).setValue(place.trim());
            }
        }
        modsTypeClient.setLocation(newLocationList);
    }

    @Override
    public void modifyPhysDescrList(String extent) {
        /** Extent part */
        List<PhysicalDescriptionTypeClient> newPhysDescrList;
        if (!isNotNullOrEmpty(modsTypeClient.getPhysicalDescription())) {
            newPhysDescrList = new ArrayList<PhysicalDescriptionTypeClient>();
            newPhysDescrList.add(new PhysicalDescriptionTypeClient());
        } else {
            newPhysDescrList = modsTypeClient.getPhysicalDescription();
        }

        if (extent == null || extent.trim().equals("")) {
            if (isNotNullOrEmpty(newPhysDescrList.get(0).getExtent())) {
                newPhysDescrList.get(0).getExtent().remove(0);
            }
        } else {
            if (isNotNullOrEmpty(newPhysDescrList.get(0).getExtent())) {
                newPhysDescrList.get(0).getExtent().remove(0);
            } else {
                newPhysDescrList.get(0).setExtent(new ArrayList<String>());
            }
            newPhysDescrList.get(0).getExtent().add(0, extent.trim());
        }
        modsTypeClient.setPhysicalDescription(newPhysDescrList);
    }

    @Override
    public void modifyNote(String note) {
        List<PhysicalDescriptionTypeClient> newPhysDescrList;
        if (!isNotNullOrEmpty(modsTypeClient.getPhysicalDescription())) {
            newPhysDescrList = new ArrayList<PhysicalDescriptionTypeClient>();
            newPhysDescrList.add(new PhysicalDescriptionTypeClient());
        } else {
            newPhysDescrList = modsTypeClient.getPhysicalDescription();
        }
        if (note == null || note.trim().equals("")) {
            if (isNotNullOrEmpty(newPhysDescrList.get(0).getNote())) {
                newPhysDescrList.get(0).getNote().remove(0);
            }
        } else {
            if (isNotNullOrEmpty(newPhysDescrList.get(0).getNote())) {
                newPhysDescrList.get(0).getNote().get(0).setValue(note);
            } else {
                List<NoteTypeClient> noteTypeClients = new ArrayList<NoteTypeClient>();
                NoteTypeClient noteTypeClient = new NoteTypeClient();
                noteTypeClient.setValue(note);
                noteTypeClients.add(noteTypeClient);
                newPhysDescrList.get(0).setNote(noteTypeClients);
            }
        }
        modsTypeClient.setPhysicalDescription(newPhysDescrList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyPartNumber(String partNumber) {
        /** Title info part */
        List<TitleInfoTypeClient> newTitleInfo;
        if (!isNotNullOrEmpty(modsTypeClient.getTitleInfo())) {
            newTitleInfo = new ArrayList<TitleInfoTypeClient>();
            newTitleInfo.add(new TitleInfoTypeClient());
        } else {
            newTitleInfo = modsTypeClient.getTitleInfo();
        }

        if (partNumber == null || partNumber.trim().equals("")) {

            if (isNotNullOrEmpty(newTitleInfo.get(0).getPartNumber())) {
                newTitleInfo.get(0).getPartNumber().remove(0);
            }
        } else {
            if (isNotNullOrEmpty(newTitleInfo.get(0).getPartNumber())) {
                newTitleInfo.get(0).getPartNumber().remove(0);
            } else {
                newTitleInfo.get(0).setPartNumber(new ArrayList<String>());
            }
            newTitleInfo.get(0).getPartNumber().add(0, partNumber.trim());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyLevelName(String levelName) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyType(String type) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyPartName(String partNumber) {
        // TODO Auto-generated method stub

    }
}
