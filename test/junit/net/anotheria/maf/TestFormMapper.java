package net.anotheria.maf;

import net.anotheria.maf.util.FormObjectMapper;
import net.anotheria.util.mapper.PopulateWith;
import net.anotheria.webutils.servlet.request.MockServletRequestFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestFormMapper {
	private static final String CONTEXT_PATH = "/";
	private static final String SERVER_NAME = "localhost";
	private static final String PARAM_RECIPIENT_ID = "rec_id";

	@Test
	public void shouldMapRequestParameterToModelField() {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("a1", "1");
		params.put(PARAM_RECIPIENT_ID, "12");

		Map<String, Object> attributes = new HashMap<String, Object>();
		HttpServletRequest req = MockServletRequestFactory.createMockedRequest(params, attributes, CONTEXT_PATH, SERVER_NAME, Locale.ENGLISH, 80);
		CompositeObject destination = new CompositeObject();
		// when
		FormObjectMapper.map(req, destination);

		// then
		Assert.assertEquals(Integer.valueOf(params.get("a1")), Integer.valueOf(destination.getA1()));

	}


	public class CompositeObject {
		private static final String PARAM_SUBJECT = "subject";
		private static final String PARAM_SEND_AUTOANSEWER = "auto_ans";		

		@PopulateWith(value = PARAM_RECIPIENT_ID)
		private String recipientId;

		@PopulateWith(value = PARAM_SUBJECT)
		private String subject;

		@PopulateWith(value = PARAM_SEND_AUTOANSEWER)
		private String sendAutoAnswer;

		private A a;
		private int a1;

		public A getA() {
			return a;
		}

		public void setA(A a) {
			this.a = a;
		}

		public class A {
			private int sizeOfA;

			public int getSizeOfA() {
				return sizeOfA;
			}

			public void setSizeOfA(int sizeOfA) {
				this.sizeOfA = sizeOfA;
			}
		}

		public int getA1() {
			return a1;
		}

		public void setA1(int a1) {
			this.a1 = a1;
		}

		public String getRecipientId() {
			return recipientId;
		}

		public void setRecipientId(String recipientId) {
			this.recipientId = recipientId;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getSendAutoAnswer() {
			return sendAutoAnswer;
		}

		public void setSendAutoAnswer(String sendAutoAnswer) {
			this.sendAutoAnswer = sendAutoAnswer;
		}
	}
}