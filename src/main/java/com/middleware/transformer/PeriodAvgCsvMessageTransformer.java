/**
 * 
 */
package com.middleware.transformer;

import java.text.DateFormat;
import java.util.Date;
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
public class PeriodAvgCsvMessageTransformer extends AbstractMessageTransformer {

//	EUR,USD,2015-10-24T21:00:00+0000,1.10155,1.10195
//	USD,INR,2014/09/30,2014/09/30,Period Average,59.63
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		if (StringUtils.isNotEmpty(message.getPayload().toString())) {
			
			String csvEntry = message.getPayload().toString().split("\\n")[1];
			
			String[] splits = csvEntry.split(",");
//			TODO
			String date = "2015/09/27";
			
//			StringBuilder sb = new StringBuilder();
//			sb.append("source_currency,target_currency,date1,date2,currency_type,rate");
//			sb.append("\n");
//			sb.append(splits[0]+","+ splits[1] + "," + date + "," + date + ",Period Average," + splits[2]);
//			
//			return sb.toString();
			
			Map<String, String> map = new HashMap<>();
			map.put("source_currency", splits[0]);
			map.put("target_currency", splits[1]);
			map.put("date1", date);
			map.put("date2", date);
			map.put("currency_type", "Period Average");
			map.put("rate", splits[2]);
			
			return map;
			
		} else {
			return null;
		}
	}

}
