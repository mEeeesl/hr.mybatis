package com.my.hr.presentation;

public enum Job {
	EXIT("����"), LIST("���"), ADD("�߰�"), FIX("����"), DEL("����");
	
	private String label;

	private Job (String label) {
		this.label = label;
	} 
	private static Job toJob(int ordinal) {
		return Job.values()[ordinal];
	}
	
	public static String labels() {
		Job[] jobs = values();
		String last = "";
		String line = "";
		
		for(Job job: jobs) {
			if(job.ordinal() == 0) last = job.ordinal() + ". " + job.label;
			else line += job.ordinal() + ". " + job.label + ", ";
		}
			line += last;
		
		return line;
	}
}

/*

Job.values() = [EXIT, LIST, ADD, FIX, DEL]
Job.values().length = 5

values()[index]
values()[0] = EXIT
values()[1] = LIST

job.ordinal() => 0, 1, 2, 3, 4
job.label => ����, ���, �߰�, ����, ����

*/