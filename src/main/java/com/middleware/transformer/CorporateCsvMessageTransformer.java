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
public class CorporateCsvMessageTransformer extends AbstractMessageTransformer {

//	EUR,USD,2015-10-24T21:00:00+0000,1.10175
//	USD,INR,2014/09/30,2014/09/30,Corporate,60
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		if (StringUtils.isNotEmpty(message.getPayload().toString())) {
			
			String csvEntry = message.getPayload().toString().split("\\n")[1];
			
			String[] splits = csvEntry.split(",");
//			TODO
			String date = splits[2].substring(0, 10).replace('-', '/');
			
//			StringBuilder sb = new StringBuilder();
//			sb.append("source_currency,target_currency,date1,date2,currency_type,rate");
//			sb.append("\n");
//			sb.append(splits[0]+","+ splits[1] + "," + date + "," + date + ",Corporate," + splits[3]);
//			return sb.toString();
			
			Map<String, String> map = new HashMap<>();
			map.put("source_currency", splits[0]);
			map.put("target_currency", splits[1]);
			map.put("date1", date);
			map.put("date2", date);
			map.put("currency_type", "Corporate");
			map.put("rate", splits[3]);
			
			return map;

		} else {
			return null;
		}
	}

}
