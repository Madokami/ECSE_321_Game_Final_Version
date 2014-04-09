package data;

import java.io.Serializable;

import player.Player_Homura;
import player.Player_Kyouko;
import player.Player_Madoka;
import player.Player_Mami;
import player.Player_Sayaka;

/**
* <b>Description:</b>
* <br>
* Details Player Data
* <br>ex. Hp, mp, level, etc.
* <br>Includes default data for characters
* <br>Details for each individual character
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class PlayerData implements Serializable {
	public double saHp,saMaxHp,saMp,saMaxMp,saSoul,saMaxSoul,saExpCurrent;
	public int saBombStrength,saBombLength,saSpd,saLevel,saBP;
	public static int saHpLimit,saMpLimit,saSpdLimit,saSoulLimit,saBombSpdLimit,saBombStrengthLimit,saBombLengthLimit;
	
	public double hoHp,hoMaxHp,hoMp,hoMaxMp,hoSoul,hoMaxSoul,hoExpCurrent;
	public int hoBombStrength,hoBombLength,hoSpd,hoLevel,hoBP;
	public static int hoHpLimit,hoMpLimit,hoSpdLimit,hoSoulLimit,hoBombSpdLimit,hoBombStrengthLimit,hoBombLengthLimit;
	
	public double mdHp,mdMaxHp,mdMp,mdMaxMp,mdSoul,mdMaxSoul,mdExpCurrent;
	public int mdBombStrength,mdBombLength,mdSpd,mdLevel,mdBP;
	public static int mdHpLimit,mdMpLimit,mdSpdLimit,mdSoulLimit,mdBombSpdLimit,mdBombStrengthLimit,mdBombLengthLimit;
	
	public double maHp,maMaxHp,maMp,maMaxMp,maSoul,maMaxSoul,maExpCurrent;
	public int maBombStrength,maBombLength,maSpd,maLevel,maBP;
	public static int maHpLimit,maMpLimit,maSpdLimit,maSoulLimit,maBombSpdLimit,maBombStrengthLimit,maBombLengthLimit;
	
	public double kyHp,kyMaxHp,kyMp,kyMaxMp,kySoul,kyMaxSoul,kyBombSpd,kyExpCurrent;
	public int kyBombStrength,kyBombLength,kySpd,kyLevel,kyBP;
	public static int kyHpLimit,kyMpLimit,kySpdLimit,kySoulLimit,kyBombSpdLimit,kyBombStrengthLimit,kyBombLengthLimit;
	
	/**
	 * Default data for characters
	 */
	public void loadDefaultValues(){
		saHp=100;
		//saMaxHp=100;
		saMp=200;
		//saMaxMp=200;
		saSpd=12;
		saSoul=500;
		//saMaxSoul=500;
		saBombStrength = 40;
		saBombLength = 3;
		saExpCurrent = 0;
		saLevel = 1;
		saBP=10;
		
		
		hoHp=100;
		//hoMaxHp=100;
		hoMp=200;
		//hoMaxMp=200;
		hoSpd=12;
		hoSoul=500;
		//hoMaxSoul=500;
		hoBombStrength = 40;
		hoBombLength = 3;
		hoExpCurrent = 0;
		hoLevel=1;
		hoBP=10;
		
		mdHp=100;
		//mdMaxHp=100;
		mdMp=200;
		//mdMaxMp=200;
		mdSpd=12;
		mdSoul=500;
		//mdMaxSoul=500;
		mdBombStrength = 40;
		mdBombLength = 3;
		mdExpCurrent=0;
		mdLevel = 1;
		mdBP=10;
		
		maHp=100;
		//maMaxHp=100;
		maMp=200;
		//maMaxMp=200;
		maSpd=12;
		maSoul=500;
		//maMaxSoul=500;
		maBombStrength = 40;
		maBombLength = 3;
		maExpCurrent=0;
		maLevel=1;
		maBP=10;
		
		kyHp=100;
		//kyMaxHp=100;
		kyMp=200;
		//kyMaxMp=200;
		kySpd=12;
		kySoul=500;
		//kyMaxSoul=500;
		kyBombStrength = 40;
		kyBombLength = 3;
		kyExpCurrent=0;
		kyLevel = 1;
		kyBP=10;
	}
	
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void loadPlayerStatus(Player_Sayaka sa){
		sa.hp=saHp;
		//sa.maxHp=saMaxHp;
		sa.mp=saMp;
		//sa.maxMp=saMaxMp;
		sa.spd=saSpd;
		sa.bombStrength=saBombStrength;
		sa.bombLength=saBombLength;
		sa.soul=saSoul;
		//sa.maxSoul=saMaxSoul;
		sa.expCurrent =saExpCurrent;
		sa.level=saLevel;
		sa.BP=saBP;
	}
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void loadPlayerStatus(Player_Homura ho){
		ho.hp=hoHp;
		//ho.maxHp=hoMaxHp;
		ho.mp=hoMp;
		//ho.maxMp=hoMaxMp;
		ho.spd=hoSpd;
		ho.bombStrength=hoBombStrength;
		ho.bombLength=hoBombLength;
		ho.soul=hoSoul;
		//ho.maxSoul=hoMaxSoul;
		ho.expCurrent = hoExpCurrent;
		ho.level=hoLevel;
		ho.BP=hoBP;
	}
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void loadPlayerStatus(Player_Madoka md){
		md.hp=mdHp;
		//md.maxHp=mdMaxHp;
		md.mp=mdMp;
		//md.maxMp=mdMaxMp;
		md.spd=mdSpd;
		md.bombStrength=mdBombStrength;
		md.bombLength=mdBombLength;
		md.soul=mdSoul;
		//md.maxSoul=mdMaxSoul;
		md.expCurrent =mdExpCurrent;
		md.level=mdLevel;
		md.BP=mdBP;
	}
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void loadPlayerStatus(Player_Mami ma){
		ma.hp=maHp;
		//ma.maxHp=maMaxHp;
		ma.mp=maMp;
		//ma.maxMp=maMaxMp;
		ma.spd=maSpd;
		ma.bombStrength=maBombStrength;
		ma.bombLength=maBombLength;
		ma.soul=maSoul;
		//ma.maxSoul=maMaxSoul;
		ma.expCurrent =maExpCurrent;
		ma.level=maLevel;
		ma.BP=maBP;
	}
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void loadPlayerStatus(Player_Kyouko	ky){
		ky.hp=kyHp;
		//ky.maxHp=kyMaxHp;
		ky.mp=kyMp;
		//ky.maxMp=kyMaxMp;
		ky.spd=kySpd;
		ky.bombStrength=kyBombStrength;
		ky.bombLength=kyBombLength;
		ky.soul=kySoul;
		//ky.maxSoul=kyMaxSoul;
		ky.expCurrent =kyExpCurrent;
		ky.level=kyLevel;
		ky.BP=kyBP;
	}
	
	//these methods will update the PlayerData Class with current values in the Player Class. 
	//This could doing so could result in the player getting permanently buffed with temp bonus.
	/**
	 * updates player status and attributes
	 * @param player object
	 */
	public void upDatePlayerData(Player_Sayaka sa){
		/*
		saHp=sa.hp;
		saMaxHp=sa.maxHp;
		saMp=sa.mp;
		saMaxMp=sa.maxMp;
		saSpd=sa.spd;
		saBombStrength=sa.bombStrength;
		saBombLength=sa.bombLength;
		saSoul=sa.soul;
		saMaxSoul=sa.maxSoul;
		*/
		saExpCurrent=sa.expCurrent;
		saLevel=sa.level;
		saBP=sa.BP;
	}
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void upDatePlayerData(Player_Homura ho){
		/*
		hoHp=ho.hp;
		hoMaxHp=ho.maxHp;
		hoMp=ho.mp;
		hoMaxMp=ho.maxMp;
		hoSpd=ho.spd;
		hoBombStrength=ho.bombStrength;
		hoBombLength=ho.bombLength;
		hoSoul=ho.soul;
		hoMaxSoul=ho.maxSoul;
		*/
		hoExpCurrent=ho.expCurrent;
		hoLevel=ho.level;
		hoBP=ho.BP;
	}
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void upDatePlayerData(Player_Madoka md){
		/*
		mdHp=md.hp;
		mdMaxHp=md.maxHp;
		mdMp=md.mp;
		mdMaxMp=md.maxMp;
		mdSpd=md.spd;
		mdBombStrength=md.bombStrength;
		mdBombLength=md.bombLength;
		mdSoul=md.soul;
		mdMaxSoul=md.maxSoul;
		*/
		mdExpCurrent=md.expCurrent;
		mdLevel=md.level;
		mdBP=md.BP;
	}
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void upDatePlayerData(Player_Mami ma){
		/*
		maHp=ma.hp;
		maMaxHp=ma.maxHp;
		maMp=ma.mp;
		maMaxMp=ma.maxMp;
		maSpd=ma.spd;
		maBombStrength=ma.bombStrength;
		maBombLength=ma.bombLength;
		maSoul=ma.soul;
		maMaxSoul=ma.maxSoul;
		*/
		maExpCurrent=ma.expCurrent;
		maLevel=ma.level;
		maBP=ma.BP;
	}
	/**
	 * loads player status and attributes
	 * @param player object
	 */
	public void upDatePlayerData(Player_Kyouko ky){
		/*
		kyHp=ky.hp;
		kyMaxHp=ky.maxHp;
		kyMp=ky.mp;
		kyMaxMp=ky.maxMp;
		kySpd=ky.spd;
		kyBombStrength=ky.bombStrength;
		kyBombLength=ky.bombLength;
		kySoul=ky.soul;
		kyMaxSoul=ky.maxSoul;
		*/
		kyExpCurrent=ky.expCurrent;
		kyLevel=ky.level;
		kyBP=ky.BP;
	}
}
