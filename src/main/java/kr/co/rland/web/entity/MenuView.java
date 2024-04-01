package kr.co.rland.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuView {

	  private long id;
	  private String korName;
	  private String engName; 
	  private int price; 
	  private String img;
      private Date regDate;
	  private long categoryId;
	  private int likeCount; 
}
