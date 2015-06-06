package microsoft.exchange.webservices.data.misc.availability;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(JUnit4.class)
public class TimeWindowTest {

	TimeWindow impl;
	Date dummyStartDate;
	Date dummyEndDate;
	 final String DateOnlyFormat = "yyyy-MM-dd'T'00:00:00";
	 DateFormat formatter = new SimpleDateFormat(DateOnlyFormat);
	  @Mock EwsServiceXmlReader reader;
	  @Mock EwsServiceXmlWriter writer;
	  
	  /**
	   * Initializes objects annotated with Mockito annotations for given testClass
	   * @throws Exception
	   */
	  @Before public void setUp() throws Exception {
		    impl = new TimeWindow();
		    MockitoAnnotations.initMocks(this);
		    dummyStartDate = formatter.parse("2015-06-02T00:00:00");
		    dummyEndDate = formatter.parse("2015-06-05T00:00:00");
		  }
	  
	  @Test 
	  public void testRead_StartTime_AttributesFromXml() throws Exception {
		    doReturn(dummyStartDate).when(reader).readElementValueAsDateTime(XmlNamespace.Types, XmlElementNames.StartTime);
		    impl.loadFromXml(reader);
		    assertEquals(dummyStartDate, impl.getStartTime());
		  }
	  
	  @Test 
	  public void testRead_EndTime_AttributesFromXml() throws Exception {
		    doReturn(dummyEndDate).when(reader).readElementValueAsDateTime(XmlNamespace.Types, XmlElementNames.EndTime);
		    impl.loadFromXml(reader);
		    assertEquals(dummyEndDate, impl.getEndTime());
		  }
	  
	  @Test 
	  public void testWrite_StartTime_ToXml() throws Exception {
		  	impl.setStartTime(dummyStartDate); 
		    impl.writeToXml(writer, XmlElementNames.StartTime);
		    verify(writer).writeElementValue(XmlNamespace.Types, XmlElementNames.StartTime,
		    		dummyStartDate);
		  }
	  
	  @Test 
	  public void testWrite_EndTime_ToXml() throws Exception {
		  	impl.setEndTime(dummyEndDate); 
		    impl.writeToXml(writer, XmlElementNames.EndTime);
		    verify(writer).writeElementValue(XmlNamespace.Types, XmlElementNames.EndTime,
		    		dummyEndDate);
		  }
	  
	  @Test 
	  public void testWrite_StartTime_ToXml_UnscopedDatesOnly() throws Exception {
		  	impl.setStartTime(dummyStartDate); 
		  	impl.setEndTime(dummyEndDate); 
		    impl.writeToXmlUnscopedDatesOnly(writer, XmlElementNames.StartTime);
		    verify(writer).writeElementValue(XmlNamespace.Types, XmlElementNames.StartTime,
		    		formatter.format(dummyStartDate));
		  }
	  
	  @Test 
	  public void testWrite_EndTime_ToXml_UnscopedDatesOnly() throws Exception {
		  	impl.setStartTime(dummyStartDate); 
		  	impl.setEndTime(dummyEndDate); 
		    impl.writeToXmlUnscopedDatesOnly(writer, XmlElementNames.EndTime);
		    verify(writer).writeElementValue(XmlNamespace.Types, XmlElementNames.EndTime,
		    		formatter.format(dummyEndDate));
		  }
}
