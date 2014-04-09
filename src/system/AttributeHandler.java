package system;
/**
* Description:
* Set game character data and images
* @author Team 6
* @version 1.0
* @since 2014-03-27
*/
import game.Game;
import graphicEffects.IntToImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import data.PlayerData;
import menu.MenuChar;
import menu.MenuChar.CHARACTER;

public class AttributeHandler {
	public PlayerData pData;
	
	public double hpOriginal,mpOriginal,soulOriginal;
	public int bombStrengthOriginal,bombLengthOriginal,BPOriginal,spdOriginal,levelOriginal;	
	public double hpCur,mpCur,soulCur;
	public int bombStrengthCur,bombLengthCur,BPCur,spdCur,levelCur;
	
	public BufferedImage[] hp,mp,soul,spd,bombStrength,bombLength,BPCurImg,BPOriImg,levelImg;
	
	public int hpCost = 1, hpValue = 5;
	public int mpCost = 1, mpValue = 5;
	public int soulCost = 1, soulValue = 10;
	public int spdCost = 5, spdValue = 1;
	public int bombStrengthCost=1, bombStrengthValue=1;
	public int bombLengthCost=10, bombLengthValue=1;
	
	
	
	private int attributeX = MenuChar.statsX+130;
	private int attributeY = MenuChar.statsY+35;
	
	
	public AttributeHandler(Game game){
		this.pData=game.getPlayerData();
		refreshAll();
		
	}

	/**
	 * sets the original values of the parameters as well as the current value;
	 *
	 */
	public void updateOriginalValue() {
		if(MenuChar.cSelected==CHARACTER.SAYAKA){
			hpOriginal=pData.saHp;
			mpOriginal=pData.saMp;
			soulOriginal=pData.saSoul;
			spdOriginal=pData.saSpd;
			bombStrengthOriginal=pData.saBombStrength;
			bombLengthOriginal=pData.saBombLength;
			BPOriginal=pData.saBP;
			levelOriginal=pData.saLevel;
		}
		else if(MenuChar.cSelected==CHARACTER.HOMURA){
			hpOriginal=pData.hoHp;
			mpOriginal=pData.hoMp;
			soulOriginal=pData.hoSoul;
			spdOriginal=pData.hoSpd;
			bombStrengthOriginal=pData.hoBombStrength;
			bombLengthOriginal=pData.hoBombLength;
			BPOriginal=pData.hoBP;
			levelOriginal=pData.hoLevel;
		}
		else if(MenuChar.cSelected==CHARACTER.MAMI){
			hpOriginal=pData.maHp;
			mpOriginal=pData.maMp;
			soulOriginal=pData.maSoul;
			spdOriginal=pData.maSpd;
			bombStrengthOriginal=pData.maBombStrength;
			bombLengthOriginal=pData.maBombLength;
			BPOriginal=pData.maBP;
			levelOriginal=pData.maLevel;
		}
		else if(MenuChar.cSelected==CHARACTER.MADOKA){
			hpOriginal=pData.mdHp;
			mpOriginal=pData.mdMp;
			soulOriginal=pData.mdSoul;
			spdOriginal=pData.mdSpd;
			bombStrengthOriginal=pData.mdBombStrength;
			bombLengthOriginal=pData.mdBombLength;
			BPOriginal=pData.mdBP;
			levelOriginal=pData.mdLevel;
		}
		else if(MenuChar.cSelected==CHARACTER.KYOUKO){
			hpOriginal=pData.kyHp;
			mpOriginal=pData.kyMp;
			soulOriginal=pData.kySoul;
			spdOriginal=pData.kySpd;
			bombStrengthOriginal=pData.kyBombStrength;
			bombLengthOriginal=pData.kyBombLength;
			BPOriginal=pData.kyBP;
			levelOriginal=pData.kyLevel;
		}	
	}
	
	/**
	 * set current game character data
	 */
	public void setCurrentValue(){
		hpCur=hpOriginal;
		mpCur=mpOriginal;
		soulCur=soulOriginal;
		spdCur=spdOriginal;
		bombStrengthCur=bombStrengthOriginal;
		bombLengthCur=bombLengthOriginal;
		BPCur=BPOriginal;
		levelCur=levelOriginal;
	}
	
	/**
	 * set new game character data
	 */
	public void setNewValues(){
		if(MenuChar.cSelected==CHARACTER.SAYAKA){
			pData.saHp=hpCur;
			pData.saMp=mpCur;
			pData.saSoul=soulCur;
			pData.saSpd=spdCur;
			pData.saBombStrength=bombStrengthCur;
			pData.saBombLength=bombLengthCur;
			pData.saBP=BPCur;
		}
		else if(MenuChar.cSelected==CHARACTER.HOMURA){
			pData.hoHp=hpCur;
			pData.hoMp=mpCur;
			pData.hoSoul=soulCur;
			pData.hoSpd=spdCur;
			pData.hoBombStrength=bombStrengthCur;
			pData.hoBombLength=bombLengthCur;
			pData.hoBP=BPCur;
		}
		else if(MenuChar.cSelected==CHARACTER.MAMI){
			pData.maHp=hpCur;
			pData.maMp=mpCur;
			pData.maSoul=soulCur;
			pData.maSpd=spdCur;
			pData.maBombStrength=bombStrengthCur;
			pData.maBombLength=bombLengthCur;
			pData.maBP=BPCur;
		}
		else if(MenuChar.cSelected==CHARACTER.MADOKA){
			pData.mdHp=hpCur;
			pData.mdMp=mpCur;
			pData.mdSoul=soulCur;
			pData.mdSpd=spdCur;
			pData.mdBombStrength=bombStrengthCur;
			pData.mdBombLength=bombLengthCur;
			pData.mdBP=BPCur;
		}
		else if(MenuChar.cSelected==CHARACTER.KYOUKO){
			pData.kyHp=hpCur;
			pData.kyMp=mpCur;
			pData.kySoul=soulCur;
			pData.kySpd=spdCur;
			pData.kyBombStrength=bombStrengthCur;
			pData.kyBombLength=bombLengthCur;
			pData.kyBP=BPCur;
		}	
	}

	/**let's you restore the original values if you press cancel or smth
	 * 
	 */
	public void restoreOriginalValue(){
		if(MenuChar.cSelected==CHARACTER.SAYAKA){
			pData.saHp=hpOriginal;
			pData.saMp=mpOriginal;
			pData.saSoul=soulOriginal;
			pData.saSpd=spdOriginal;
			pData.saBombStrength=bombStrengthOriginal;
			pData.saBombLength=bombLengthOriginal;
			pData.saBP=BPOriginal;
		}
		else if(MenuChar.cSelected==CHARACTER.HOMURA){
			pData.hoHp=hpOriginal;
			pData.hoMp=mpOriginal;
			pData.hoSoul=soulOriginal;
			pData.hoSpd=spdOriginal;
			pData.hoBombStrength=bombStrengthOriginal;
			pData.hoBombLength=bombLengthOriginal;
			pData.hoBP=BPOriginal;
		}
		else if(MenuChar.cSelected==CHARACTER.MAMI){
			pData.maHp=hpOriginal;
			pData.maMp=mpOriginal;
			pData.maSoul=soulOriginal;
			pData.maSpd=spdOriginal;
			pData.maBombStrength=bombStrengthOriginal;
			pData.maBombLength=bombLengthOriginal;
			pData.maBP=BPOriginal;
		}
		else if(MenuChar.cSelected==CHARACTER.MADOKA){
			pData.mdHp=hpOriginal;
			pData.mdMp=mpOriginal;
			pData.mdSoul=soulOriginal;
			pData.mdSpd=spdOriginal;
			pData.mdBombStrength=bombStrengthOriginal;
			pData.mdBombLength=bombLengthOriginal;
			pData.mdBP=BPOriginal;
		}
		else if(MenuChar.cSelected==CHARACTER.KYOUKO){
			pData.kyHp=hpOriginal;
			pData.kyMp=mpOriginal;
			pData.kySoul=soulOriginal;
			pData.kySpd=spdOriginal;
			pData.kyBombStrength=bombStrengthOriginal;
			pData.kyBombLength=bombLengthOriginal;
			pData.kyBP=BPOriginal;
		}	
	}
	
	/**
	 * sets the image arrays that are rendered onto screen
	 * 
	 */
	public void setImageArrays(){
		hp=IntToImage.toImageSmall((int) hpCur);
		mp=IntToImage.toImageSmall((int)mpCur);
		soul=IntToImage.toImageSmall((int)soulCur);
		spd=IntToImage.toImageSmall(spdCur);
		bombStrength=IntToImage.toImageSmall(bombStrengthCur);
		bombLength=IntToImage.toImageSmall(bombLengthCur);
		BPCurImg=IntToImage.toImageSmall(BPCur);
		BPOriImg=IntToImage.toImageSmall(BPOriginal);
		levelImg=IntToImage.toImageDamage(levelCur);
	}
	
	/**
	 * draw character data images on the screen
	 * @param g current graphic
	 */
	public void render(Graphics g){
		for(int i=0;i<BPCurImg.length;i++){
			g.drawImage(BPCurImg[i], attributeX+12*i+65, attributeY-25, null);
		}
		for(int i=0;i<BPOriImg.length;i++){
			g.drawImage(BPOriImg[i], attributeX+12*i+102, attributeY-25, null);
		}
		for(int i=0;i<hp.length;i++){
			g.drawImage(hp[i], attributeX+12*i, attributeY, null);
		}
		for(int i=0;i<mp.length;i++){
			g.drawImage(mp[i], attributeX+12*i, attributeY+MenuChar.statsShift, null);
		}
		for(int i=0;i<soul.length;i++){
			g.drawImage(soul[i], attributeX+12*i, attributeY+MenuChar.statsShift*2, null);
		}
		for(int i=0;i<spd.length;i++){
			g.drawImage(spd[i], attributeX+12*i, attributeY+MenuChar.statsShift*3, null);
		}
		for(int i=0;i<bombStrength.length;i++){
			g.drawImage(bombStrength[i], attributeX+12*i, attributeY+MenuChar.statsShift*4, null);
		}
		for(int i=0;i<bombLength.length;i++){
			g.drawImage(bombLength[i], attributeX+12*i, attributeY+MenuChar.statsShift*5, null);
		}
		g.setFont(new Font("serif",Font.BOLD,22));
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("cost "+this.hpCost+" pt", attributeX+150, attributeY+15);
		g.drawString("cost "+mpCost+" pt", attributeX+150, attributeY+15+MenuChar.statsShift);
		g.drawString("cost "+soulCost+" pt", attributeX+150, attributeY+15+2*MenuChar.statsShift);
		g.drawString("cost "+spdCost+" pt", attributeX+150, attributeY+15+3*MenuChar.statsShift);
		g.drawString("cost "+this.bombStrengthCost+" pt", attributeX+150, attributeY+15+4*MenuChar.statsShift);
		g.drawString("cost "+this.bombLengthCost+" pt", attributeX+150, attributeY+15+5*MenuChar.statsShift);
	}
	
	/**
	 * update game character data and images
	 */
	public void refreshAll(){
		updateOriginalValue();
		setCurrentValue();
		this.setImageArrays();
	}
	
	/**
	 * Refresh images rendered onto screen
	 */
	public void refreshImage(){
		this.setImageArrays();
	}
	
}
