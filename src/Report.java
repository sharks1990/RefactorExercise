//Ronan Sharkey C10303937 DT354 
import java.util.*;
public class Report {

	public Report() {
	}

	Hashtable<Integer, ArrayList<String>> offeringToName = new Hashtable<Integer, ArrayList<String>>();

	public void findStudentData() throws Exception {// Changed name of populate map to something connected to the project
		List<Schedule> schedules = SchedulePersistence.all();//Changed to list
		for (Schedule schedule : schedules) {
			for (Offering offering : schedule.offerings) {
				populateMapFor(schedule, offering);
			}
		}
	}

	private void populateMapFor(Schedule schedule, Offering offering) {
		ArrayList<String> list = (ArrayList<String>)offeringToName.get(new Integer(offering.getId()));
		if (list == null) {
			list = new ArrayList<String>();
			offeringToName.put(new Integer(offering.getId()), list);
		}
		list.add(schedule.name);
	}

	public void writeOffering(StringBuffer buffer, ArrayList<String> list, Offering offering) {
		buffer.append(offering.getCourse().getName() + " " + offering.getDaysTimes() + "\n");
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String s = (String) iterator.next();
			buffer.append("\t" + s + "\n");
		}
	}

	public void write(StringBuffer buffer) throws Exception {
		findStudentData();// Changed name
		Enumeration<Integer> enumeration = (Enumeration<Integer>)offeringToName.keys();
		while (enumeration.hasMoreElements()) {
			Integer offeringId = (Integer)enumeration.nextElement();
			ArrayList<String> list = (ArrayList<String>)offeringToName.get(offeringId);
			writeOffering(buffer, list, OfferingPersistence.find(offeringId.intValue()));
		}
		buffer.append("Number of scheduled offerings: ");
		buffer.append(offeringToName.size());
		buffer.append("\n");
	}
}