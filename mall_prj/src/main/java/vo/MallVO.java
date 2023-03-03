package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MallVO {
	
	private int pno;
	private String pname;
	private String pinfo;
	private String pimg;
	private int pprice;
	private int pstock;
}
