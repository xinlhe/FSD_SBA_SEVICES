package net.bluefsd.upload;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.ibm.sba.microservice.smc.upload.SBA_MicroService_UploadClient_Config;
import net.bluefsd.main.BaseTestController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import({ SBA_MicroService_UploadClient_Config.class })
public class UploadControllerTest extends BaseTestController {
	
	
	//@Test
	public void add55()  throws IOException{
		String filePath = "C:\\lydoc\\FSD\\finalassess\\500112-5.xlsx";
		testAdd(filePath);
	}

//	//@Test
	public void add56() throws IOException {
		String filePath = "C:\\lydoc\\FSD\\finalassess\\500112-6.xlsx";
		testAdd(filePath);
	}

	//@Test
	public void add65()  throws IOException{
		String filePath = "C:\\lydoc\\FSD\\finalassess\\600116-5.xlsx";
		testAdd(filePath);
	}

	//@Test
	public void add66() throws IOException {
		String filePath = "C:\\lydoc\\FSD\\finalassess\\600116-6.xlsx";
		testAdd(filePath);
	}
	
	@Test
	public void add865()  throws IOException{
		String filePath = "C:\\lydoc\\FSD\\finalassess\\800118-5.xlsx";
		testAdd(filePath);
	}

	//@Test
	public void add86() throws IOException {
		String filePath = "C:\\lydoc\\FSD\\finalassess\\800118-6.xlsx";
		testAdd(filePath);
	}
	
	public void testAdd(String filePath) throws IOException {

		// String filePath = "C:\\lydoc\\FSD\\finalassess\\500112-5.xlsx";

		int index = filePath.lastIndexOf("\\");
		String fileName = filePath.substring(index + 1, filePath.length());
		System.out.println("-------" + fileName);

		try {
			FileInputStream fis = new FileInputStream(filePath);

			MockMultipartFile file = new MockMultipartFile("file", fileName, "multipart/form-data", fis);

			ResultActions matcher = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload/excel").file(file));

			MvcResult mvcResult = matcher.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String result = mvcResult.getResponse().getContentAsString();

			matcher.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
