/**
 * 
 */
package com.middleware.transformer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

/**
 * @author sumvedshami
 *
 */
public class PeriodEndCsvMessageTransformer extends AbstractMessageTransformer {

//	EUR,USD,2015-10-24T21:00:00+0000,1.10175
//	USD,INR,2014/09/30,2014/09/30,Corporate,60
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		
		System.out.println("Period end response: " + message.getPayload().toString());
		
		if (StringUtils.isNotEmpty(message.getPayload().toString())) {
			
			String csvEntry = message.getPayload().toString().split("\\n")[1];
			
			String[] splits = csvEntry.split(",");
//			TODO
			String date = splits[2].substring(0, 10).replace('-', '/');
			
			Map<String, String> map = new HashMap<>();
			map.put("source_currency", splits[0]);
			map.put("target_currency", splits[1]);
			map.put("date1", date);
			map.put("date2", date);
			map.put("currency_type", "Period End");
			map.put("rate", splits[3]);
			
			message.setPayload(map);
			
			return map;
			
		} else {
			return null;
		}
	}

}
