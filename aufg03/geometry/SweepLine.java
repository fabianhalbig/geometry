package geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SweepLine {

	ArrayList<Strecke> strecken;
	
	public SweepLine(ArrayList<Strecke> strecken) {
		this.strecken = strecken;
	}
	
	public ArrayList<Punkt> sweep(){
		
		ArrayList<Punkt> eventPunkte = new ArrayList<Punkt>();
		for(Strecke strecke : this.strecken){
			eventPunkte.add(strecke.start);
			eventPunkte.add(strecke.end);
		}
		
		Collections.sort(eventPunkte, new Comparator<Punkt>() {
			@Override
			public int compare(Punkt p1, Punkt p2) {
				return Double.compare(p1.x, p2.x);
			}
		});
		
		ArrayList<Strecke> sweepLine = new ArrayList<Strecke>();
		ArrayList<Punkt> schnittPunkte = new ArrayList<Punkt>();
		
		for(Punkt events:eventPunkte){
			if(events.punkTyp == "start"){
				Strecke segment = new Strecke();
				/*
				Let segE = E's segment;
				Add segE to SL;
				Let segA = the segment above segE in SL;
				Let segB = the segment below segE in SL;
				If (I = Intersect( segE with segA) exists)
				Insert I into x;
				If (I = Intersect( segE with segB) exists)
				Insert I into x;
				 */
			}
			if(events.punkTyp == "end"){
							/*
							TreatRightEndpoint() {
							Let segE = E's segment;
							Let segA = the segment above segE in SL;
							Let segB = the segment below segE in SL;
							Remove segE from SL;
							If (I = Intersect( segA with segB) exists)
							If (I is not in x already) Insert I into x;
}
							 */
						}
			if(events.punkTyp == "schnitt"){
				/*
				Add E to the output list L;
				Let segE1 above segE2 be E's intersecting segments in SL;
				Swap their positions so that segE2 is now above segE1;
				Let segA = the segment above segE2 in SL;
				Let segB = the segment below segE1 in SL;
				If (I = Intersect(segE2 with segA) exists)
				If (I is not in x already) Insert I into x;
				If (I = Intersect(segE1 with segB) exists)
				If (I is not in x already) Insert I into x;
				 */
			}
		}
		return schnittPunkte;
	}
	
	public void startPunkt(ArrayList<Punkt> eventPunkte, ArrayList<Punkt> sweepLine, ArrayList<Punkt> schnittPunkte) {
		
	}
}
