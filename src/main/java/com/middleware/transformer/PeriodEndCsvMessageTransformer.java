/**
 * 
 */
package com.middleware.transformer;

import java.text.DateFormat;
import java.util.Date;

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
		if (StringUtils.isNotEmpty(message.getPayload().toString())) {
			
			String csvEntry = message.getPayload().toString().split("\\n")[1];
			
			String[] splits = csvEntry.split(",");
//			TODO
			String date = splits[2].substring(0, 10).replace('-', '/');
			String result = splits[0]+","+ splits[1] + "," + date + "," + date + ",Period End," + splits[3];
			return result;
		} else {
			return null;
		}
	}

}
