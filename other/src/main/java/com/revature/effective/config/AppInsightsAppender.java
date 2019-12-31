package com.revature.effective.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.microsoft.applicationinsights.internal.common.LogTelemetryClientProxy;
import com.microsoft.applicationinsights.internal.common.TelemetryClientProxy;
import com.microsoft.applicationinsights.internal.logger.InternalLogger;

/**
 * LOGBack appender.
 */
public class AppInsightsAppender extends AppenderBase<ILoggingEvent> {

    // region Members

    private boolean isInitialized = false;
    private LogTelemetryClientProxy logTelemetryClientProxy;
    private String instrumentationKey;

    // endregion Members

    // region Public methods

    public TelemetryClientProxy getTelemetryClientProxy() {
        return this.logTelemetryClientProxy;
    }

    /**
     * Sets the instrumentation key.
     *
     * @param instrumentationKey The instrumentation key.
     */
    public void setInstrumentationKey(String instrumentationKey) {
        this.instrumentationKey = instrumentationKey;
    }

    /**
     * Appends the new event.
     * Catching exceptions and check if the appender has been started is not necessary
     * as it all taken care by the AppenderBase class.
     *
     * @param eventObject The event to append.
     */
    @Override
    protected void append(ILoggingEvent eventObject) {
        if (!this.isStarted() || !this.isInitialized) {

            // TODO: trace not started or not initialized.
            return;
        }

        AppInsightsEvent aiEvent = new AppInsightsEvent(eventObject);
        this.logTelemetryClientProxy.sendEvent(aiEvent);
    }

    @Override
    public void start() {
        super.start();

        try {
            logTelemetryClientProxy = new LogTelemetryClientProxy(instrumentationKey);
            this.isInitialized = true;
        } catch (Exception e) {
            // Appender failure must not fail the running application.
            this.isInitialized = false;
            InternalLogger.INSTANCE.error("Failed to initialize appender with exception: %s.", e.toString());
        }
    }
}