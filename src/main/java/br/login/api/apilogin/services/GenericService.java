package br.login.api.apilogin.services;

import br.login.api.apilogin.entitys.GenericEntity;
import br.login.api.apilogin.repositorys.GenericRepository;

public abstract class GenericService <E extends GenericEntity>{

    protected abstract GenericRepository <E> getRepository() ;
    protected  abstract void validate (E entidade) throws Exception;
    public final E save (E entidade )throws Exception{
        if (entidade == null)
            throw new Exception("Objeto não pode ser nulo");
        this.validate(entidade);

        return this.getRepository().save(entidade);
    }




}
