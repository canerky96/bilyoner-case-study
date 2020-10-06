package com.bilyoner.assignment.balanceapi.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.bilyoner.assignment.balanceapi.exception.BaseRuntimeException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import com.bilyoner.assignment.balanceapi.exception.HttpAwareErrorCode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageTextResolver {

	private List<MessageSource> registeredMessageSourceList = new ArrayList<>();

	public void registerMessageSource(MessageSource messageSource) {
		this.registeredMessageSourceList.add(messageSource);
	}

	public String resolve(HttpAwareErrorCode errorCode, Locale locale, Object... args) {
		String key = errorCode.prefix() + "-" + errorCode.code();
		String resolvedMessageText = resolve(key, args, locale);

		if (resolvedMessageText == null)
			resolvedMessageText = errorCode.code().toString();

		return resolvedMessageText;
	}

	public String resolve(BaseRuntimeException bre, Locale locale) {
		if (bre.getErrorCode() == null) {
			return bre.getMessage();
		}

		String key = bre.getErrorCode().prefix() + "-" + bre.getErrorCode().code();
		String resolvedMessageText = resolve(key, bre.getParams(), locale);

		if (resolvedMessageText == null) {
			resolvedMessageText = bre.getMessage();
		}

		return resolvedMessageText;
	}

	public String resolve(String key, Object[] args, Locale locale) {
		String resolvedMessageText = null;
		for (MessageSource messageSource : registeredMessageSourceList) {
			try {
				String message = messageSource.getMessage(key, args, locale);
				if (! key.equals(message)) {
					resolvedMessageText = message;
				}
			} catch (NoSuchMessageException nsme) {
			}

			if (resolvedMessageText != null) {
				break;
			}
		}

		if (resolvedMessageText == null) {
			resolvedMessageText = key;
		}

		return resolvedMessageText;
	}
}

