package com.bcp.bancamovil.web.business.services.user;

import org.springframework.integration.message.GenericMessage;
import org.springframework.stereotype.Service;

import com.bcp.bancamovil.dom.pojos.CredimasBM;
import com.bcp.bancamovil.dom.util.ActionConstants;
import com.bcp.bancamovil.dom.util.CustomExceptionBM;

@Service
public class DojoKeyboardService<T> {

    private static final String CREDIMAS_NUMER = "0123456789123456";
    
    public static String getCredimasNumer() {
		return CREDIMAS_NUMER;
	}
    
    public GenericMessage<CredimasBM> validatePinToken(GenericMessage<CredimasBM> message) throws CustomExceptionBM {
			// Get de content Message
			CredimasBM credimasBM = message.getPayload();

			credimasBM.setAuditAction(ActionConstants.VALIDATE_TOKEN);

			if ( credimasBM.getIdCredimas( ) == 1 ) {
				if (credimasBM.getCredimasNumber().equals( DojoKeyboardService.getCredimasNumer() ) ){
					credimasBM.setPass( "000000" );
					return message;
				}else{
					throw new CustomExceptionBM("MessageUserLoginErr.incorrectCredimas", false);
				}
			}else{
				throw new CustomExceptionBM("MessageUserLoginErr.incorrectCredimas", false);
			}
    }

	

}
