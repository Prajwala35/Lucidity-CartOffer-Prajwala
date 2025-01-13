package com.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.controller.ApplyOfferRequest;
import com.springboot.controller.OfferRequest;
import com.springboot.controller.SegmentResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.springboot.controller.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartOfferApplicationTests {

	// Prajwala Tests --> Begin

	// Testcases for FlatX offer type

	@Test
	public void applyFLATXofferforSegment1() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(200, 1, 1);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("190", result);
	}

	@Test
	public void applyFLATXofferforDifferentSegment() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(200, 1, 2);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("200", result);
	}

	@Test
	public void applyFLATXofferforDifferentRestaurant() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(200, 2, 1);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("200", result);
	}

	@Test
	public void applyFLATXofferforZeroCartValue() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(0, 1, 1);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("0", result);
	}

	@Test
	public void applyFLATXofferforNegativeCartValue() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(-10, 1, 1);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("0", result);
	}

	@Test
	public void applyFLATXofferforCartValueLessThanDiscount() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(9, 1, 1);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("9", result);
	}

	@Test
	public void applyFLATXofferforCartValueEqualToDiscount() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(10, 1, 1);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("0", result);
	}

	@Test
	public void applyZeroOffer() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(10, 1, 1);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("0", result);
	}

	// Add X value as zero in offer for restaurant 3 and segment 3

	@Test
	public void checkFlat0ForSegment3() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p3");
		OfferRequest offerRequest = new OfferRequest(3, "FLATX", 0, segments);
		boolean result = addOffer(offerRequest);
		Assert.assertEquals(result, true); // able to add offer
	}

	@Test
	public void checkzeroOfferForSegment3() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(10, 3, 3);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("0", result);
	}

	// AddingFlatX% offer for user segment2 and restaurant 2

	@Test
	public void checkFlatXPercentageForSegment2() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p2");
		OfferRequest offerRequest = new OfferRequest(2, "FLATXPercentage", 10, segments);
		boolean result = addOffer(offerRequest);
		Assert.assertEquals(result, true); // able to add offer
	}

	@Test
	public void applyFLATXPercOfferforSegment1() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(200, 2, 2);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("180", result);
	}

	@Test
	public void applyFLATXPercOfferforDifferentSegment() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(200, 2, 1);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("200", result);
	}

	@Test
	public void applyFLATXPercOfferforDifferentRestaurant() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(200, 1, 2);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("200", result);
	}

	@Test
	public void applyFLATXPercofferforZeroCartValue() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(0, 2, 2);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("0", result);
	}

	@Test
	public void applyFLATXPercOfferforNegativeCartValue() throws Exception {
		ApplyOfferRequest off = new ApplyOfferRequest(-10, 2, 2);
		StringBuffer result = applyOfferForDiscount(off);
		Assert.assertEquals("0", result);
	}

	// Prajwala tests -->End

	@Test
	public void checkFlatXForOneSegment() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p1");
		OfferRequest offerRequest = new OfferRequest(1, "FLATX", 10, segments);
		boolean result = addOffer(offerRequest);
		Assert.assertEquals(result, true); // able to add offer
	}

	public boolean addOffer(OfferRequest offerRequest) throws Exception {
		String urlString = "http://localhost:9001/api/v1/offer";
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		String POST_PARAMS = mapper.writeValueAsString(offerRequest);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
		return true;
	}

	public StringBuffer applyOfferForDiscount(ApplyOfferRequest OffRqst) throws Exception {
		boolean flag = false;
		StringBuffer response = new StringBuffer();
		String urlString = "http://localhost:9001/api/v1/offer";
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");
		ObjectMapper mapper = new ObjectMapper();
		String POST_PARAMS = mapper.writeValueAsString(OffRqst);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
		return response;
	}
}