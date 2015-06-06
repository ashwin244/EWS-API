/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;
import microsoft.exchange.webservices.data.core.exception.misc.FormatException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.folder.CalendarFolder;
import microsoft.exchange.webservices.data.core.service.folder.ContactsFolder;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.folder.SearchFolder;
import microsoft.exchange.webservices.data.core.service.folder.TasksFolder;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.core.service.item.Contact;
import microsoft.exchange.webservices.data.core.service.item.ContactGroup;
import microsoft.exchange.webservices.data.core.service.item.Conversation;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.item.MeetingCancellation;
import microsoft.exchange.webservices.data.core.service.item.MeetingMessage;
import microsoft.exchange.webservices.data.core.service.item.MeetingRequest;
import microsoft.exchange.webservices.data.core.service.item.MeetingResponse;
import microsoft.exchange.webservices.data.core.service.item.PostItem;
import microsoft.exchange.webservices.data.core.service.item.Task;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinitionBase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.stream.XMLStreamException;

@RunWith(JUnit4.class)
public class EwsUtilitiesTest {

	@Mock ISelfValidate selfValidate;
	@Mock ServiceObject serviceObject;
	
	   @Before public void initMocks() {
	       MockitoAnnotations.initMocks(this);
	   }
	   
  @Test
  public void testGetBuildVersion() {
    assertEquals("Build version must be 0s", "0.0.0.0", EwsUtilities.getBuildVersion());
  }

  @Test
  public void testGetItemTypeFromXmlElementName() {
    assertEquals(Task.class, EwsUtilities.getItemTypeFromXmlElementName("Task"));
    assertEquals(EmailMessage.class, EwsUtilities.getItemTypeFromXmlElementName("Message"));
    assertEquals(PostItem.class, EwsUtilities.getItemTypeFromXmlElementName("PostItem"));
    assertEquals(SearchFolder.class, EwsUtilities.getItemTypeFromXmlElementName("SearchFolder"));
    assertEquals(Conversation.class, EwsUtilities.getItemTypeFromXmlElementName("Conversation"));
    assertEquals(Folder.class, EwsUtilities.getItemTypeFromXmlElementName("Folder"));
    assertEquals(CalendarFolder.class, EwsUtilities.getItemTypeFromXmlElementName("CalendarFolder"));
    assertEquals(MeetingMessage.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingMessage"));
    assertEquals(Contact.class, EwsUtilities.getItemTypeFromXmlElementName("Contact"));
    assertEquals(Item.class, EwsUtilities.getItemTypeFromXmlElementName("Item"));
    assertEquals(Appointment.class, EwsUtilities.getItemTypeFromXmlElementName("CalendarItem"));
    assertEquals(ContactsFolder.class, EwsUtilities.getItemTypeFromXmlElementName("ContactsFolder"));
    assertEquals(MeetingRequest.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingRequest"));
    assertEquals(TasksFolder.class, EwsUtilities.getItemTypeFromXmlElementName("TasksFolder"));
    assertEquals(MeetingCancellation.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingCancellation"));
    assertEquals(MeetingResponse.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingResponse"));
    assertEquals(ContactGroup.class, EwsUtilities.getItemTypeFromXmlElementName("DistributionList"));
  }

  @Test
  public void testEwsAssert() {
    EwsUtilities.ewsAssert(true, null, null);

    try {
      EwsUtilities.ewsAssert(false, "a", "b");
    } catch (final RuntimeException ex) {
      assertEquals("[a] b", ex.getMessage());
    }
  }

  @Test
  public void testParseBigInt() throws ParseException {
    assertEquals(BigInteger.TEN, EwsUtilities.parse(BigInteger.class, BigInteger.TEN.toString()));
  }

  @Test
  public void testParseBigDec() throws ParseException {
    assertEquals(BigDecimal.TEN, EwsUtilities.parse(BigDecimal.class, BigDecimal.TEN.toString()));
  }

  @Test
  public void testParseString() throws ParseException {
    final String input = "lorem ipsum dolor sit amet";
    assertEquals(input, EwsUtilities.parse(input.getClass(), input));
  }

  @Test
  public void testParseDouble() throws ParseException {
    Double input = Double.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0.0;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Double.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseInteger() throws ParseException {
    Integer input = Integer.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Integer.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseBoolean() throws ParseException {
    Boolean input = Boolean.TRUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Boolean.FALSE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseLong() throws ParseException {
    Long input = Long.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0l;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Long.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseFloat() throws ParseException {
    Float input = Float.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0f;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Float.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseShort() throws ParseException {
    Short input = Short.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Short.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseByte() throws ParseException {
    Byte input = Byte.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Byte.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseDate() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String input = sdf.format(new Date());
    assertEquals(input, EwsUtilities.parse(input.getClass(), input));
  }

  @Test
  public void testParseNullValue() throws ParseException {
    final String input = null;
    assertEquals(input, EwsUtilities.parse(String.class, input));
  }
  
  
  //*** Adding new test cases to increase LOC
  @Test
  public void testFormatLogMessage() throws XMLStreamException, IOException{ 
	    Date d = new Date();
	    String entryKind = "testTag";
	    String logEntry = "testLog";
	    
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");
	    df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String formattedString = df.format(d);
	    
	    final String input = "<Trace Tag=\""+entryKind+"\" Tid=\"1\" Time=\""+formattedString+"\">\r\n"+logEntry+"\r\n</Trace>\r\n";
	  assertEquals(input, EwsUtilities.formatLogMessage(entryKind, logEntry));
  }
  
  @Test
  public void testFormatHttpResponseHeaders() throws EWSHttpException {  
	  Map<String, String> map = new HashMap<String, String>();
	  HttpWebRequest response = Mockito.mock(HttpWebRequest.class);
	  map.put("test", "test");
	  Mockito.when(response.getResponseHeaders()).thenReturn(map);
	  Mockito.when(response.getResponseContentType()).thenReturn("testContentType");
	    final String input = "0 testContentType\ntest : test\n\n";
	  assertEquals(input, EwsUtilities.formatHttpResponseHeaders(response));
  }

  
  @Test
  public void testFormatHttpRequestHeaders() throws URISyntaxException, EWSHttpException, MalformedURLException {    
	  Map<String, String> map = new HashMap<String, String>();
	  HttpWebRequest request = Mockito.mock(HttpWebRequest.class);
	  map.put("test", "test");
	  URL url = new URL("http://www.testURI.com/");
	  Mockito.when(request.getRequestMethod()).thenReturn("testRequestMethod");
	  Mockito.when(request.getUrl()).thenReturn(url);
	  Mockito.when(request.getRequestProperty()).thenReturn(map);
	    final String input = "TESTREQUESTMETHOD / HTTP/1.1\ntest : test\n\n";
	  assertEquals(input,EwsUtilities.formatHttpRequestHeaders(request));
  }
  
  @Test
  public void testGetTimeSpanToXSDuration(){
	  assertEquals(EwsUtilities.getTimeSpanToXSDuration(new TimeSpan(Long.MAX_VALUE)), "P106751991167DT2562047788015H12M55.0S");
  }
  
  @Test
  public void testCorrect_DomainFromEmailAddress(){
	  assertEquals(EwsUtilities.domainFromEmailAddress("test@outlook.com"), "outlook.com");
  }
  
  @Test( expected= FormatException.class)
  public void testInCorrect_DomainFromEmailAddress(){
	  assertEquals(EwsUtilities.domainFromEmailAddress("test@outlook@com"), "outlook.com");
  }
  
  
  @Test(expected= Exception.class)
  public void testValidateParamAllowNull() throws Exception{ 
	  Mockito.when(serviceObject.isNew()).thenReturn(true);
	  EwsUtilities.validateParamAllowNull(serviceObject, null);
  }
  
  @Test
  public void testValidateParamCollection() throws Exception{ 
	  List<String> collection = new ArrayList<String>();
	  collection.add("test1");
	  collection.add("test2");
	      Iterator<String> property = collection.iterator();
	   String paramName = null;
	  EwsUtilities.validateParamCollection(property, paramName); 
  }
  
  @Test(expected= IllegalArgumentException.class)
  public void testValidateParam_Empty_Collection() throws Exception{ 
	  List<String> collection = new ArrayList<String>();
	      Iterator<String> property = collection.iterator();
	   String paramName = null;
	  EwsUtilities.validateParamCollection(property, paramName); 
  }
  
  
  @Test(expected= Exception.class)
  public void testValidateParam() throws Exception{ 
	    String property = "";
	   String paramName = "failedParam";
	  EwsUtilities.validateParam(property, paramName); 
  }
}
