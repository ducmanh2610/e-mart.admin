package asset_admin.dao;

import java.util.Optional;

import asset_admin.entities.Attempts;

public interface IAttemptDAO {
	public Optional<Attempts> findAttemptByUsername(String username);

	public Attempts createNewAttempt(Attempts attempts);
}
