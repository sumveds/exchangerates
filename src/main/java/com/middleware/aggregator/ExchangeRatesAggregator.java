/**
 * 
 */
package com.middleware.aggregator;

import java.util.List;
import java.util.ListIterator;

import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.context.MuleContextAware;
import org.mule.api.routing.AggregationContext;
import org.mule.api.routing.RouterResultsHandler;
import org.mule.routing.AggregationStrategy;
import org.mule.routing.DefaultRouterResultsHandler;

/**
 * @author sumvedshami
 *
 */
public class ExchangeRatesAggregator implements AggregationStrategy, MuleContextAware {
	
	private RouterResultsHandler resultsHandler = new DefaultRouterResultsHandler();
	private MuleContext muleContext;

	/* (non-Javadoc)
	 * @see org.mule.routing.AggregationStrategy#aggregate(org.mule.api.routing.AggregationContext)
	 */
	@Override
	public MuleEvent aggregate(AggregationContext context) throws MuleException {
		
		List<MuleEvent> events = context.collectEventsWithoutExceptions();
		
		ListIterator<MuleEvent> itr = events.listIterator();
		
		while(itr.hasNext()) {
			Object payload = itr.next().getMessage().getPayload();
			if (payload instanceof org.mule.transport.NullPayload) {
				itr.remove();
			}
		}
		
		return resultsHandler.aggregateResults(events, context.getOriginalEvent(), muleContext);
	}
	
	@Override
	public void setMuleContext(MuleContext context) {
		this.muleContext = context;
	}

}
