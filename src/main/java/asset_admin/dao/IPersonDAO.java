package asset_admin.dao;

import java.util.List;

import asset_admin.entities.Person;

public interface IPersonDAO {

	public List<Person> listAllPerson();

	public boolean deletePersonById(int deptId);

	public Person addNewPerson(Person Person);

	public boolean updatePersonById(Person Person);

	public Person selectPersonById(int deptId);

	public List<Person> selectPersonByName(String deptName);

}
