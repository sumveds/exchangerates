/**
 * 
 */
package com.middleware.transformer;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transport.NullPayload;

import com.middleware.input.Data;

/**
 * @author sumvedshami
 *
 */
public class InputCsvTransformer extends AbstractMessageTransformer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mule.transformer.AbstractMessageTransformer#transformMessage(org.
	 * mule.api.MuleMessage, java.lang.String)
	 */
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		// TODO Auto-generated method stub
		
		Object payload = message.getPayload();
		
		if (!(payload instanceof NullPayload)) {
			
			List<Data> list = new ArrayList<>();
			String data = payload.toString();
//			System.out.println("Data from csv: " + data);
			
			String[] rows = data.split("\\n");
			
			for (String row : rows) {
				
				String[] currencies = row.split(",");
				String sourceCurrency = currencies[0];
				String targetCurrency = currencies[1];
				
				Data input = new Data();
				input.setSourceCurrency(sourceCurrency);
				input.setTargetCurrency(targetCurrency);
				
				System.out.println("Csv row: " + input);
				
				list.add(input);
			}

			return list;
		} else {
			return null;
		}
	}

}
