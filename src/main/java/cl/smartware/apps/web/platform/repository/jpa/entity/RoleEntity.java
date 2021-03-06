package cl.smartware.apps.web.platform.repository.jpa.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "WP_ROLE")
public class RoleEntity implements EntityBase
{
	private static final long serialVersionUID = 328155766745861459L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Integer id;

	@Column(name = "NAME", length = 30, nullable = false)
	private String name;

	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = true;

	@Column(name = "CREATED_AT", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt = new Timestamp(Calendar.getInstance().getTimeInMillis());

	@OneToMany(mappedBy = "role")
	private List<RoleUserEntity> roleUsers = new ArrayList<>();

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive()
	{
		return this.active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Boolean active)
	{
		this.active = active;
	}

	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt()
	{
		return this.createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt)
	{
		this.createdAt = createdAt;
	}

	/**
	 * @return the roleUsers
	 */
	public List<RoleUserEntity> getRoleUsers()
	{
		return this.roleUsers;
	}

	/**
	 * @param roleUsers
	 *            the roleUsers to set
	 */
	public void setRoleUsers(List<RoleUserEntity> roleUsers)
	{
		this.roleUsers = roleUsers;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RoleEntity roleEntity = (RoleEntity) o;
		return Objects.equals(id, roleEntity.id);
	}

	public enum ERole
	{
		ROLE_ADMIN, ROLE_USER;
	}
}
