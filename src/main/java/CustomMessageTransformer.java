import org.apache.commons.lang3.StringUtils;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;


public class CustomMessageTransformer extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		
		if (StringUtils.isNotEmpty(message.getPayload().toString())) {
			return message.getPayload().toString().split("\\n")[1];
		} else {
			return null;
		}
	}

}
