package com.goorm.devlink.profileservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.Locale;

@RequiredArgsConstructor
public class MessageUtil {

    private final MessageSource messageSource;

    public String getProfileCreateMessage() {
        return getMessage("response.profile.create");
    }

    public String getProfileUpdateMessage() {
        return getMessage("response.profile.update");
    }

    public String getProfileDeleteMessage() {
        return getMessage("response.profile.delete");
    }

    public String getScheduleCreateMessage() {
        return getMessage("response.schedule.create");
    }

    public String getScheduleCancelMessage() {
        return getMessage("response.schedule.cancel");
    }

    public String getUserUuidEmptyMessage() {
        return getMessage("request.empty.userUuid");
    }

    public String getMentoringUuidEmptyMessage() {
        return getMessage("request.empty.mentoringUuid");
    }

    public String getUserUuidNoSuchMessage(String userUuid) {
        return getMessage("request.nosuch.userUuid", new String[] {userUuid});
    }

    public String getScheduleCreateOverlapMessage() {
        return getMessage("request.overlap.schedule.create");
    }

    public String getUnsupportedFileTypeErrorMessage() {
        return getMessage("request.error.file-upload.unsupported");
    }

    public String getProfileCreateErrorMessage() {
        return getMessage("runtime.error.profile.create");
    }

    public String getProfileUpdateErrorMessage() {
        return getMessage("runtime.error.profile.update");
    }

    public String getCalendarCreateErrorMessage() {
        return getMessage("runtime.error.calendar.create");
    }

    public String getScheduleCreateErrorMessage() {
        return getMessage("runtime.error.schedule.create");
    }

    public String getScheduleCancelErrorMessage() {
        return getMessage("runtime.error.schedule.cancel");
    }

    private String getMessage(String messageCode) {
        return messageSource.getMessage(messageCode, new String[]{}, Locale.KOREA);
    }

    private String getMessage(String messageCode, String[] parameters) {
        return messageSource.getMessage(messageCode, parameters, Locale.KOREA);
    }
}
