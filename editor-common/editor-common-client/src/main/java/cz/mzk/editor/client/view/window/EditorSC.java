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

package cz.mzk.editor.client.view.window;

import com.smartgwt.client.util.SC;

import cz.mzk.editor.client.LangConstants;
import cz.mzk.editor.shared.rpc.LockInfo;

/**
 * @author Matous Jobanek
 * @version $Id$
 */

public final class EditorSC {

    public enum ConfigFieldType {
        NUMBER, STRING, URL
    }

    public static final void objectIsLock(LangConstants lang, LockInfo lockInfo) {

        SC.say(lang.objectIsLocked(), getObjectIsLockMessage(lang, lockInfo, null));
    }

    public static final String getObjectIsLockMessage(LangConstants lang,
                                                      LockInfo lockInfo,
                                                      String otherMessage) {
        StringBuffer objectLockedBuffer = new StringBuffer();
        String lockDescription = lockInfo.getLockDescription();

        if ("".equals(lockInfo.getLockOwner())) {
            objectLockedBuffer.append(lang.lockedByUser());
            objectLockedBuffer.append(": ").append("<br/>").append("<br/>");
            objectLockedBuffer.append("".equals(lockDescription) ? lang.noDescription() : lockDescription);

        } else {
            objectLockedBuffer.append(lang.objectLockedBy());
            objectLockedBuffer.append(": ").append("<br/>").append("<br/>");
            objectLockedBuffer.append(lockInfo.getLockOwner());
            objectLockedBuffer.append("<br/>").append("<br/>");
            objectLockedBuffer.append(lang.withDescription());
            objectLockedBuffer.append(": ").append("<br/>").append("<br/>");
            objectLockedBuffer.append("".equals(lockDescription.trim()) ? lang.noDescription()
                    : lockDescription);
        }
        objectLockedBuffer.append("<br/>").append("<br/>");
        objectLockedBuffer.append(lang.lockExpires() + ": "
                + createTimeToExpLock(lang, lockInfo.getTimeToExpiration())).append("<br/>");

        if (otherMessage != null) {
            objectLockedBuffer.append("<br/>").append("<br/>");
            objectLockedBuffer.append(otherMessage);
        }

        return objectLockedBuffer.toString();
    }

    private static String createTimeToExpLock(LangConstants lang, String[] parsedTimeString) {
        StringBuffer sb = new StringBuffer();

        int[] parsedTime =
                new int[] {Integer.parseInt(parsedTimeString[0]), Integer.parseInt(parsedTimeString[1]),
                        Integer.parseInt(parsedTimeString[2])};

        if (parsedTime[0] > 0) {
            sb.append(parsedTime[0]).append(" ");
            sb.append(parsedTime[0] == 1 ? lang.day() : parsedTime[0] > 4 ? lang.days5_() : lang.days2_4());
            sb.append(", ");
        }
        if (parsedTime[1] > 0) {
            sb.append(parsedTime[1]).append(" ");;
            sb.append(parsedTime[1] == 1 ? lang.hour() : parsedTime[1] > 4 ? lang.hours5_() : lang.hours2_4());
            sb.append(" ");
        }
        sb.append(lang.and() + " ");
        sb.append(parsedTime[2]).append(" ");;
        sb.append(parsedTime[2] == 1 ? lang.minute() : parsedTime[2] > 4 ? lang.minutes5_() : lang
                .minutes2_4());

        return sb.toString();
    }

    public static final void operationFailed(LangConstants lang, String message) {
        SC.say(lang.operationFailed() + (message != null ? "<br/><br/>" + message : ""));
    }

    public static final void operationSuccessful(LangConstants lang, String message) {
        SC.say(lang.operationSuccessful() + (message != null ? "<br/><br/>" + message : ""));
    }

    public static final void compulsoryConfigFieldWarn(String fieldName,
                                                       ConfigFieldType type,
                                                       LangConstants lang) {
        SC.warn("V konfiguracnim souboru neni zadan povinny atribut " + fieldName
                + ", nebo jeho hodnota neodpovida typu " + type);
    }
}
