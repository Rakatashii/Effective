package com.revature.effective.config;

import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import com.microsoft.applicationinsights.internal.common.ApplicationInsightsEvent;
import com.microsoft.applicationinsights.internal.logger.InternalLogger;
import com.microsoft.applicationinsights.telemetry.SeverityLevel;
import ch.qos.logback.classic.Level;

public final class AppInsightsEvent extends ApplicationInsightsEvent {
	
	private ILoggingEvent loggingEvent;

    public AppInsightsEvent(ILoggingEvent loggingEvent) {
        this.loggingEvent = loggingEvent;
    }

    @Override
    public String getMessage() {
        return this.loggingEvent.getFormattedMessage();
    }

    @Override
    public boolean isException() {
        return this.loggingEvent.getThrowableProxy() != null;
    }

    @Override
    public Exception getException() {
        Exception exception = null;

        if (isException()) {
            Throwable throwable = ((ThrowableProxy)this.loggingEvent.getThrowableProxy()).getThrowable();
            exception = throwable instanceof Exception ? (Exception) throwable : new Exception(throwable);
        }

        return exception;
    }

    @Override
    public Map<String, String> getCustomParameters() {
        Map<String, String> metaData = new HashMap<String, String>();

        metaData.put("SourceType", "LOGBack");

        addLogEventProperty("LoggerName", loggingEvent.getLoggerName(), metaData);
        addLogEventProperty("LoggingLevel", loggingEvent.getLevel() != null ? loggingEvent.getLevel().levelStr : null, metaData);
        addLogEventProperty("ThreadName", loggingEvent.getThreadName(), metaData);
        addLogEventProperty("TimeStamp", getFormattedDate(loggingEvent.getTimeStamp()), metaData);

        if (isException()) {
            addLogEventProperty("Logger Message", getMessage(), metaData);
        }

        for (Map.Entry<String, String> entry : loggingEvent.getMDCPropertyMap().entrySet()) {
            addLogEventProperty(entry.getKey(), entry.getValue(), metaData);
        }

        // TODO: No location info?
        // TODO: Username, domain and identity should be included as in .NET version.
        // TODO: Should check, seems that it is not included in Log4j2.

        return metaData;
    }

    @Override
    public SeverityLevel getNormalizedSeverityLevel() {
        int log4jLevelAsInt = loggingEvent.getLevel().toInt();
        switch (log4jLevelAsInt) {
            case Level.ERROR_INT: // ERROR
                return SeverityLevel.Error;

            case Level.WARN_INT: // WARN
                return SeverityLevel.Warning;

            case Level.INFO_INT: // INFO
                return SeverityLevel.Information;

            case Level.TRACE_INT: // TRACE
            case Level.DEBUG_INT: // DEBUG
            case Level.ALL_INT:   // ALL
                return SeverityLevel.Verbose;

            default:
                InternalLogger.INSTANCE.error("Unknown Logback option, %d, using TRACE level as default", log4jLevelAsInt);
                return SeverityLevel.Verbose;
        }
    }
}
