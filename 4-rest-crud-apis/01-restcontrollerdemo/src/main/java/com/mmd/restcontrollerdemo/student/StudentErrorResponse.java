package com.mmd.restcontrollerdemo.student;

public record StudentErrorResponse(int status, String message, long timeStamp) {
}
