package com.study.springboot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

@Controller
public class fileUploadController {
	@RequestMapping("/")
	public String root() {
		return "fileForm";
	}
	
	//@responseBody : 문자열 그대로 반환해준다는 뜻
	@RequestMapping("fileUpLoad")
	public @ResponseBody String fileUpLoad(HttpServletRequest request) {
		JSONObject jObj = new JSONObject();
		
//		String result= "success";
		try {
							// 파일을 업로드할 위치 설정. 루트는 resources. /upload/ => upload에 / 를 붙이면 파일 하위에 업로드하겠다는 뜻 upload
			String filePath = ResourceUtils.getFile("classpath:static/upload/").toPath().toString();
			System.out.println("파일저장위치 : " + filePath);
			
					// 파일을 가져오면 그대로 읽을수없음. stream 형태로 변환해줘야함. 그후 파일 이름이 같고 사이즈가 0보다 크면 
			List<Part> fileParts = request.getParts().stream().filter(part -> "files".equals(part.getName()) && part.getSize() > 0)
			.collect(Collectors.toList()); // 읽은 파일을 리스트화 시킴 반환형은 리스트. 그림도 스트림변환후 리스트화해서 읽을수있음 
			
			// 파일이 여러개 일때
			for(Part filePart : fileParts) {
				// 사용자가 넣은 파일이름 얻어오기
				String fileName = Paths.get(filePart.getSubmittedFileName())	//사용자가 서브밋 했을때 파일이름 가져옴
										.getFileName().toString();				// 파일이름을 문자열로 투스트링후 변수에 담음.
				
				String fpn = filePath + "\\" + fileName;		// 어디에 저장할건지 파일이름 저장과 함께 경로 설정
			try(	//try 매개변수안에 아래문구를 넣어주면 해당 실행문 다 끝나면 자동으로 종료해줌			
				// 파일을 열어서 읽을때 필요한 문구
				BufferedInputStream fin = new BufferedInputStream(filePart.getInputStream());				
				// 파일을 읽은후 저장할때 문구
				BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(fpn)))
				{
					
					int data;
					while(true) {
						data = fin.read(); // 파일을 읽은후 data에 담음
						if(data == -1)	// 파일을 다읽어서 없을경우 -1이 반환됨
							break;
						fout.write(data);	// 읽은 data 문자를 fout에 씀
								
					}
				System.out.println("Upload fileName : " + fpn);
			}catch (IOException e) {
				e.printStackTrace();
			}
			}
			jObj.put("success","ok");
			jObj.put("fileupload", "파일 업로드 성공");
		} catch (Exception e) {
			e.printStackTrace();
//			result = "fail";
			jObj.put("success","no");
			jObj.put("fileupload","파일 업로드 실패");
			
		
		}
		return jObj.toJSONString();
	}
}
