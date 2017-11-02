package com.bcp.bancamovil.web.business.services.user;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.integration.message.GenericMessage;

import com.bcp.bancamovil.dom.orm.entity.SessionEntity;
import com.bcp.bancamovil.dom.pojos.CredimasBM;
import com.bcp.bancamovil.dom.util.ApplicationConstants;
import com.bcp.bancamovil.dom.util.CustomExceptionBM;

import junit.framework.Assert;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DojoKeyboardService.class)
public class DojoKeyboardServicePowerMockitoTest {

    @InjectMocks
    private DojoKeyboardService<?> dojoKeyboardService;

    @Mock
    private ApplicationConstants applicationConstants;

    private GenericMessage<CredimasBM> messageCredimasBM;
    private CredimasBM credimasBM;

    private static final int CREDIMAS_ID = 1;
    private static final String CREDIMAS_NUMER = "0123456789123456";
    private static final String SEED = "578911382600840811224379048418867401571429689830107263950306155891760258538848980148852194444381949087387867863033754082";

    
    @Before
    public void init() {
        this.createCredimasBM();
        this.messageCredimasBM = new GenericMessage<CredimasBM>(this.credimasBM);
    }

    private void createCredimasBM() {
        SessionEntity pSession = new SessionEntity();
        pSession.setSeedCreationTime(new Date());
        pSession.setSeed(DojoKeyboardServicePowerMockitoTest.SEED);
        this.credimasBM = new CredimasBM();
        this.credimasBM.setIdCredimas(DojoKeyboardServicePowerMockitoTest.CREDIMAS_ID);
        this.credimasBM.setCredimasNumber(DojoKeyboardServicePowerMockitoTest.CREDIMAS_NUMER);
        this.credimasBM.setSession(pSession);
    }

    
    @Test
    public void validatePinTokenWhenCredimasIdOkAndCredimasNumberOkExpectedPinOk() {
        try {
        	//Arrange
            arrange();

            //Act
            this.messageCredimasBM = this.dojoKeyboardService.validatePinToken(this.messageCredimasBM);

            //Assert
            Assert.assertEquals("000000", this.messageCredimasBM.getPayload().getPass());

        } catch (CustomExceptionBM e) {
            e.printStackTrace();
        }
    }


    @Test
    public void validatePinTokenWhenCredimasIdNotOkAndCredimasNumberOkExpectedPinOk() {

    }

    @Test
    public void validatePinTokenWhenCredimasIdOkAndCredimasNumberNotOkExpectedPinOk() {

    }

    
	private void arrange() {
		PowerMockito.when(this.applicationConstants.getStringConstant("MAXIMUM_TIME_ACTIVE_KEYBOARD")).thenReturn("MAXIMUM_TIME_ACTIVE_KEYBOARD");
		PowerMockito.when(this.applicationConstants.getIntegerConstant("TIME_VALUE_SECOND")).thenReturn(60);
		PowerMockito.when(this.applicationConstants.getIntegerConstant("TIME_VALUE_MILISECOND")).thenReturn(100);

		PowerMockito.when(this.applicationConstants.getIntegerConstant("INCREASE_SEED_POSITION")).thenReturn(5);
		PowerMockito.when(this.applicationConstants.getIntegerConstant("INCREASE_SEED_VALUE")).thenReturn(6);
		PowerMockito.when(this.applicationConstants.getIntegerConstant("REPETITION_MAXIMUM_ALLOWABLE")).thenReturn(3);
		PowerMockito.when(this.applicationConstants.getIntegerConstant("REPEAT_LOOP_REPETITION_MAXIMUM_ALLOWABLE")).thenReturn(10);
	}



}
