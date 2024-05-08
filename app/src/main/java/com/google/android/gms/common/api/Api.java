package com.google.android.gms.common.api;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzo;
import com.google.android.gms.common.internal.zzv;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class Api<O extends ApiOptions> {
    private final String mName;
    private final zzb<?, O> zzPl;
    private final zze<?, O> zzPm = null;
    private final zzc<?> zzPn;
    private final zzf<?> zzPo;
    private final ArrayList<Scope> zzPp;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    public interface zza {
        void connect();

        void disconnect();

        void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        boolean isConnected();

        void zza(GoogleApiClient.zza zza);

        void zza(zzo zzo);

        void zza(zzo zzo, Set<Scope> set);

        boolean zzjM();
    }

    public interface zzb<T extends zza, O> {
        int getPriority();

        T zza(Context context, Looper looper, com.google.android.gms.common.internal.zze zze, O o, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener);
    }

    public static final class zzc<C extends zza> {
    }

    public interface zzd<T extends IInterface> {
        T zzD(IBinder iBinder);

        String zzeq();

        String zzer();
    }

    public interface zze<T extends zzd, O> {
        T zzi(O o);

        int zzkH();
    }

    public static final class zzf<C extends zzd> {
    }

    public <C extends zza> Api(String name, zzb<C, O> clientBuilder, zzc<C> clientKey, Scope... impliedScopes) {
        zzv.zzb(clientBuilder, (Object) "Cannot construct an Api with a null ClientBuilder");
        zzv.zzb(clientKey, (Object) "Cannot construct an Api with a null ClientKey");
        this.mName = name;
        this.zzPl = clientBuilder;
        this.zzPn = clientKey;
        this.zzPo = null;
        this.zzPp = new ArrayList<>(Arrays.asList(impliedScopes));
    }

    public String getName() {
        return this.mName;
    }

    public zzb<?, O> zzkC() {
        zzv.zza(this.zzPl != null, (Object) "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zzPl;
    }

    public zze<?, O> zzkD() {
        zzv.zza(this.zzPm != null, (Object) "This API was constructed with a ClientBuilder. Use getClientBuilder");
        return this.zzPm;
    }

    public List<Scope> zzkE() {
        return this.zzPp;
    }

    public zzc<?> zzkF() {
        zzv.zza(this.zzPn != null, (Object) "This API was constructed with a SimpleClientKey. Use getSimpleClientKey");
        return this.zzPn;
    }

    public boolean zzkG() {
        return this.zzPo != null;
    }
}
