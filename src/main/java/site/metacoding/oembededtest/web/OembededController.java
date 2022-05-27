package site.metacoding.oembededtest.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OembededController {
    
    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/api/oembeded")
    public ResponseEntity<?> parsing(String youtubeUrl){
        StringBuilder sb = new StringBuilder();
        try {
            String host = "https://www.youtube.com/oembed?format=json&url=";
            URL url = new URL(host+youtubeUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            
            while(true){
                String input = br.readLine();
                if(input == null) break;
                sb.append(input); 
            }
        } catch (Exception e) {
            throw new RuntimeException("잘못된 URL 주소입니다");
        }
        
        // JavaObject로 변환할 필요가 없다. 데이터가 json 형태라서!!
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }
}
