package com.my.hr.presentation;

public enum Job {
	EXIT("종료"), LIST("목록"), ADD("추가"), FIX("수정"), DEL("삭제");
	
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
job.label => 종료, 목록, 추가, 수정, 삭제

*/