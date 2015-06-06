package microsoft.exchange.webservices.data.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.TimeZone;

import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.misc.ITraceListener;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//@RunWith(JUnit4.class)
public class ExchangeServiceTest {

	//@Test
	  public void testGetPasswordExpirationDate() throws Exception {   
//			  ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
//			  	service.setUrl(new java.net.URI("https://raman.ashwin@outlook.com/EWS/Exchange.asmx"));
//		        service.setTraceEnabled(true);
//			  ExchangeCredentials credentials = new WebCredentials("raman.ashwin", "Shoba_49");
//			  service.setCredentials(credentials);
////				writer.writeElementValue(XmlNamespace.Messages,
////				        XmlElementNames.MailboxSmtpAddress,"outlook.com");
//				 
//			System.out.println(service.getPasswordExpirationDate("outlook.com").toString());
			
//			  ExchangeService service = new ExchangeService();
//			  ExchangeCredentials credentials = new WebCredentials("XXX@YYY.onmicrosoft.com", "ZZZ");
//			  service.setCredentials(credentials);
//			  URI uri = new URI("https://outlook.office365.com/EWS/Exchange.asmx");
//			  service.setUrl(uri);
//			  PropertySet itemPropertySet = new PropertySet(BasePropertySet.FirstClassProperties);
//			  itemPropertySet.setRequestedBodyType(BodyType.Text);
//			  ItemView view = new ItemView(100);
//			  view.setPropertySet(itemPropertySet);
//			  view.getOrderBy().add(ItemSchema.DateTimeReceived, SortDirection.Ascending);
//			  FindItemsResults<Item> results = service.findItems(WellKnownFolderName.Inbox, view);
//	}
	  }
}