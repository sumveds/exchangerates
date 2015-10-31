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
			String result = splits[0]+","+ splits[1] + "," + date + "," + date + ",Period Average," + splits[2];
			return result;
		} else {
			return null;
		}
	}

}
